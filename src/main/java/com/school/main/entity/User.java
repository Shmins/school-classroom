package com.school.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.school.main.entity.utils.Address;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class User implements UserDetails{
    @Id
    private String id;
    @NotNull
    @NotBlank
    private String cpf;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String password;
    @NotNull
    private Address address;
    @NotNull
    private Date dateOfBirth;

    public User(@NonNull String cpf, @NonNull String name, @NonNull String password, @NonNull Address address, @NonNull Date dateOfBirth){
        if(Boolean.FALSE.equals(verifyCpfCode(cpf))){
            throw new IllegalArgumentException("formato do cpf inválido");
        }
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean verifyCpfCode(String cpf) {
        List<Integer> list1 = this.multiplyCpf(dismemberCpf(cpf), 10, 2);
        int codeVerify1 = codeVerification(sumList(list1) % 11);

        List<Integer> list2 = this.multiplyCpf(dismemberCpf(cpf), 11, 1);
        int codeVerify2 = codeVerification(sumList(list2) % 11);

        return comparationCodeCpf(cpf, codeVerify1, codeVerify2);
    }

    private String[] dismemberCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "").split("");
    }

    private List<Integer> multiplyCpf(String[] cpf, int weightList, int length) {
        List<Integer> r = new ArrayList<>();
        int weight = weightList;
        for (int i = 0; i < cpf.length - length; ++i) {
            r.add(Integer.parseInt(cpf[i]) * weight);
            if (weight >= 1) {
                --weight;
            }
        }
        return r;
    }

    private Integer sumList(List<Integer> list) {
        return list.stream().mapToInt(Integer::intValue).sum();
    }

    private Integer codeVerification(int number) {
        if (number < 2) {
            return 0;
        } else {
            return 11 - number;
        }
    }
    private boolean comparationCodeCpf(String cpf, int code1, int code2){
        String code = String.valueOf(code1) + String.valueOf(code2);
        String cpfCode = cpf.substring(cpf.length() -2);

        return cpfCode.equals(code);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDANT"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

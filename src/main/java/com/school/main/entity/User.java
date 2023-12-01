package com.school.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
public class User implements UserDetails{
    private static final long serialVersionUID = 1905122041950251207L;
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
    @NotNull
    private Roles role;

    public User(String id, @NonNull String cpf, @NonNull String name, @NonNull String password, @NonNull Address address, @NonNull Date dateOfBirth, @NonNull Roles role){
        if(Boolean.FALSE.equals(verifyCpfCode(cpf))){
            throw new IllegalArgumentException("formato do cpf inválido");
        }
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
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
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.cpf;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}

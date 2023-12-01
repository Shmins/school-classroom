package com.school.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.school.main.entity.dto.CoordinatorDto;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "coordinator")
public class Coordinator extends User {
    public Coordinator(String id, String cpf, String name, String password, Address address, Date dateOfBirth) {
        super(id, cpf, name, password, address, dateOfBirth, Roles.ROLE_COORDINATOR);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    public CoordinatorDto convert() {
        return new CoordinatorDto(new Coordinator(this.getId(), this.getCpf(), this.getName(), this.getPassword(),
                this.getAddress(), this.getDateOfBirth()));
    }

    public List<CoordinatorDto> listConvert(List<Coordinator> list) {
        List<CoordinatorDto> coordinatorDto = new ArrayList<>();
        for (Coordinator i : list) {
            coordinatorDto.add(i.convert());
        }
        return coordinatorDto;
    }
}

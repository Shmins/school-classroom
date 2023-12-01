package com.school.main.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.school.main.entity.dto.TeacherDto;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Roles;

@Document(collection = "teacher")
public class Teacher extends User {
    public Teacher(String id, String cpf, String name, String password, Address address, Date dateOfBirth) {
        super(id, cpf, name, password, address, dateOfBirth, Roles.ROLE_TEACHER);
    }

    public TeacherDto convert() {
        return new TeacherDto(
                new Teacher(this.getId(), this.getCpf(), this.getName(), this.getPassword(), this.getAddress(),
                        this.getDateOfBirth()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

}

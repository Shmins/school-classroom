package com.school.main.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;
import com.school.main.entity.utils.Roles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

@Schema(description = "Student class")
@Document(collection = "student")
public class Student extends User {
    @NotEmpty
    private List<Responsible> responsibles;

    public Student(String id, String cpf, String name, String password, Address address, Date dateOfBirth,
            List<Responsible> responsibles) {
        super(id, cpf, name, password, address, dateOfBirth, Roles.ROLE_STUDENT);
        this.responsibles = responsibles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getRole().toString()));
    }

    public StudentDto convert() {
        return new StudentDto(new Student(this.getId(), this.getCpf(), this.getName(), this.getPassword(),
                this.getAddress(), this.getDateOfBirth(), this.getResponsibles()));
    }

    public List<Responsible> getResponsibles() {
        return this.responsibles;
    }

    public void setResponsibles(List<Responsible> responsibles) {
        this.responsibles = responsibles;
    }
}

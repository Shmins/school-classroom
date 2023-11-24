package com.school.main.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.utils.Responsible;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Student class")
@Document(collection = "student")
public class Student extends User{

    private List<Responsible> responsibles = new ArrayList<>();
    
    public Student(StudentDto studantDto) {
        super(studantDto.getCpf(), studantDto.getName(), studantDto.getPassword(), studantDto.getAddress(), studantDto.getDateOfBirth());
        if(studantDto.getResponsibles().isEmpty()){
            throw new IllegalArgumentException("Ter no minimo um responsável");
        }
        this.responsibles = studantDto.getResponsibles();
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_STUDANT"));
    }

}

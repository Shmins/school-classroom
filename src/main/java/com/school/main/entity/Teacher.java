package com.school.main.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.school.main.entity.dto.TeacherDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "teacher")
public class Teacher extends User {
    public Teacher(TeacherDto teacherDto) {
        super(
                teacherDto.getCpf(),
                teacherDto.getName(),
                teacherDto.getPassword(),
                teacherDto.getAddress(),
                teacherDto.getDateOfBirth());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_SUPER"));
    }
}

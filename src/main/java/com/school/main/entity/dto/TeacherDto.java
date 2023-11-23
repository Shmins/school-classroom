package com.school.main.entity.dto;

import java.util.Date;

import com.school.main.entity.utils.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
    private String cpf;
    private String name;
    private String password;
    private Address address;   
    private Date dateOfBirth;
}

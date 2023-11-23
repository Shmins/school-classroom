package com.school.main.entity.dto;

import java.util.Date;
import java.util.List;

import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "StudentDto class")
public class StudentDto {
    private String cpf;
    private String name;
    private String password;
    private List<Responsible> responsibles;
    private Address address;
    private Date dateOfBirth;
}

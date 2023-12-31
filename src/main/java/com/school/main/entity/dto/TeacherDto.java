package com.school.main.entity.dto;

import java.util.Date;

import com.school.main.entity.Teacher;
import com.school.main.entity.utils.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "TeacherDto class")
public class TeacherDto {
    private String id;
    private String cpf;
    private String name;
    private String password;
    private Address address;
    private Date dateOfBirth;

    public TeacherDto(Teacher teacher){
        this.id = teacher.getId();
        this.cpf = teacher.getCpf();
        this.name = teacher.getName();
        this.password = teacher.getPassword();
        this.address = teacher.getAddress();
        this.dateOfBirth = teacher.getDateOfBirth();
    }
    public Teacher convert(){
        return new Teacher(id, cpf, name, password, address, dateOfBirth);
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

    public String getPassword() {
        return password;
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
}

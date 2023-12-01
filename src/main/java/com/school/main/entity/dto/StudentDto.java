package com.school.main.entity.dto;

import java.util.Date;
import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "StudentDto class")
public class StudentDto {
    private String id;
    private String cpf;
    private String name;
    private String password;
    private List<Responsible> responsibles;
    private Address address;
    private Date dateOfBirth;

    public StudentDto(Student student){
        this.id = student.getId();
        this.cpf = student.getCpf();
        this.name = student.getName();
        this.password = student.getPassword();
        this.responsibles = student.getResponsibles();
        this.address = student.getAddress();
        this.dateOfBirth = student.getDateOfBirth();

    }
    public Student convert(){
        return new Student(this.id, this.cpf, this.name, this.password, this.address, this.dateOfBirth, this.responsibles);
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

    public List<Responsible> getResponsibles() {
        return responsibles;
    }

    public void setResponsibles(List<Responsible> responsibles) {
        this.responsibles = responsibles;
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

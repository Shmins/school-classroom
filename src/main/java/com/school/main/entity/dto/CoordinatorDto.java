package com.school.main.entity.dto;

import java.util.Date;

import com.school.main.entity.Coordinator;
import com.school.main.entity.utils.Address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "CoordinatorDto class")
public class CoordinatorDto {
    private String id;
    private String cpf;
    private String name;
    private String password;
    private Address address;   
    private Date dateOfBirth;

    public CoordinatorDto(Coordinator coordinator){
        this.id = coordinator.getId();
        this.cpf = coordinator.getCpf();
        this.name = coordinator.getName();
        this.password = coordinator.getPassword();
        this.address = coordinator.getAddress();
        this.dateOfBirth = coordinator.getDateOfBirth();
    }

    public Coordinator convert(){
        return new Coordinator(id, cpf, name, password, address, dateOfBirth);
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

}

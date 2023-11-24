package com.school.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.dto.StudentDto;
import com.school.main.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("v1/student")
public class StudentControllers {
    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Create student using schema StudentDto", tags = "Student")
    public ResponseEntity createStudent(@RequestBody @Validated StudentDto data){
        this.studentService.createStudent(data);
        return ResponseEntity.status(200).build();
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity getStudentById(@PathVariable String id){
        StudentDto result = this.studentService.getStudentById(id);
        return ResponseEntity.status(200).body(result);
    }
    @GetMapping(value = "/cpf/{cpf}")
    public ResponseEntity getStudentByCpf(@PathVariable String cpf){
        StudentDto result = this.studentService.getStudentByCpf(cpf);
        return ResponseEntity.status(200).body(result);
    }
}

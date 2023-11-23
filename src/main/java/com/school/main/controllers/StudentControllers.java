package com.school.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("v1/student")
public class StudentControllers {
    
    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Create student using schema StudentDto", tags = "Student")
    public ResponseEntity createStudent(@RequestBody @Validated StudentDto data){

        Student student = new Student(data);
        return ResponseEntity.ok(student);
    }
}

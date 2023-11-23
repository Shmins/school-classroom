package com.school.main.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;

@RestController
@RequestMapping("v1/student")
public class StudentControllers {
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity createStudent(@RequestBody StudentDto data){

        Student student = new Student(data);
        return ResponseEntity.ok(student);
    }
}

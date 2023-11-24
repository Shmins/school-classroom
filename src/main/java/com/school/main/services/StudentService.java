package com.school.main.services;

import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;

public interface StudentService {
    
    void createStudent(StudentDto student);

    List<Student> getAllStudants();

    StudentDto getStudentByCpf(String cpf);

    StudentDto getStudentByName(String name);

    StudentDto getStudentById(String id);

    void updateStudent(StudentDto student);

    void deleteById(String id);

}

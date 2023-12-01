package com.school.main.services;

import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;

public interface StudentService {
    
    Student createStudent(StudentDto student);

    List<Student> getAllStudants();

    Student getStudentByCpf(String cpf);

    Student getStudentByName(String name);

    Student getStudentById(String id);

    Student updateStudent(StudentDto student);

    void deleteById(String id);

}

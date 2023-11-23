package com.school.main.methods.services;

import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;
import com.school.main.repository.StudentRepository;
import com.school.main.services.StudentService;

public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = new Student(studentDto);
        this.studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudants() {
       return this.studentRepository.findAll();
    }

    @Override
    public Student getStudentByCpf(String cpf) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByCpf'");
    }

    @Override
    public Student getStudentByName(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentByName'");
    }

    @Override
    public Student getStudentById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentById'");
    }

    @Override
    public Student updateStudent(StudentDto student) {
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}

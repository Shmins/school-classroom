package com.school.main.methods.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;
import com.school.main.repository.StudentRepository;
import com.school.main.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(StudentDto studentDto) {
        Student student = new Student(
                studentDto.getId(),
                studentDto.getCpf(),
                studentDto.getName(),
                new BCryptPasswordEncoder().encode(studentDto.getPassword()),
                studentDto.getAddress(),
                studentDto.getDateOfBirth(),
                studentDto.getResponsibles());
        this.unique(student.getCpf());
        return this.studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudants() {

        return this.studentRepository.findAll();
    }

    @Override
    public Student getStudentByCpf(String cpf) {
        return this.studentRepository.findByCpf(cpf);
    }

    @Override
    public Student getStudentByName(String name) {
        return this.studentRepository.findByName(name);
    }

    @Override
    public Student getStudentById(String id) {
        Optional<Student> student = this.studentRepository.findById(id);
        return student.orElse(null);
    }

    @Override
    @Transactional
    public Student updateStudent(StudentDto data) {
        Student student = this.getStudentById(data.getId());
        if (data.getCpf() != null) {
            this.unique(data.getCpf());
        }
        student.setCpf(data.getCpf() != null ? data.getCpf() : student.getCpf());
        student.setName(data.getName() != null ? data.getName() : student.getName());
        student.setPassword(data.getCpf() != null ? data.getPassword() : student.getPassword());
        student.setAddress(data.getAddress() != null ? data.getAddress() : student.getAddress());
        student.setResponsibles(data.getResponsibles() != null ? data.getResponsibles() : student.getResponsibles());
        student.setDateOfBirth(data.getDateOfBirth() != null ? data.getDateOfBirth() : student.getDateOfBirth());
        this.studentRepository.save(student);
        return student;
    }

    @Override
    public void deleteById(String id) {
        this.studentRepository.deleteById(id);
    }

    private void unique(String cpf) {
        if (studentRepository.findByCpf(cpf) != null) {
            throw new IllegalStateException("dados dublicados");
        }
    }

}

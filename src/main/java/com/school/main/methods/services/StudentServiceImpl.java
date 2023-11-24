package com.school.main.methods.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.main.entity.Student;
import com.school.main.entity.dto.StudentDto;
import com.school.main.repository.StudentRepository;
import com.school.main.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
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
    public StudentDto getStudentByCpf(String cpf) {

        StudentDto studentDto = new StudentDto();
        Student student = this.studentRepository.findByCpf(cpf);

        studentDto.setCpf(student.getCpf());
        return studentDto;
    }

    @Override
    public StudentDto getStudentByName(String name) {
        Student student = this.studentRepository.findByName(name);
        return new StudentDto(
                student.getCpf(),
                student.getName(),
                student.getPassword(),
                student.getResponsibles(),
                student.getAddress(),
                student.getDateOfBirth());
    }

    @Override
    public StudentDto getStudentById(String id) {
        Optional<Student> student = this.studentRepository.findById(id);
        if(student.isPresent()){
            return new StudentDto(
                    student.get().getCpf(),
                    student.get().getName(),
                    student.get().getPassword(),
                    student.get().getResponsibles(),
                    student.get().getAddress(),
                    student.get().getDateOfBirth());
        }else{
            return null;
        }
    }

    @Override
    public void updateStudent(StudentDto data) {
        Student student = new Student(data);
        this.studentRepository.save(student);
    }

    @Override
    public void deleteById(String id) {
        this.studentRepository.deleteById(id);
    }

}

package com.school.main.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.main.entity.Student;
import com.school.main.utils.StudentModel;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    private Student student;
    @InjectMocks
    private StudentModel studentModel;

    @BeforeEach
    void setup() {
        student = studentModel.returnExampleStudent();
    }

    @Test
    void createStudentAndReturnStudent() {
        Student result = this.studentRepository.save(student);
        studentModel.assertFunctionStudent(student, result);
        student = result;
    }

    @Test
    void findStudentByIdAndReturnStudentCorrect() {
        Optional<Student> result = this.studentRepository.findById(student.getId());
        assertTrue(result.isPresent());
        studentModel.assertFunctionStudent(student, result.get());
    }

    @Test
    void findStudentByIdAndReturnNull() {
        Optional<Student> result = this.studentRepository.findById("");
        assertFalse(result.isPresent());
    }

    @Test
    void findStudentByCpfAndReturnStudentCorrect() {
        Student result = this.studentRepository.findByCpf(student.getCpf());
        studentModel.assertFunctionStudent(student, result);
    }

    @Test
    void findStudentByCpfAndReturnNull() {
        Student result = this.studentRepository.findByCpf("");
        assertNull(result);
    }
}

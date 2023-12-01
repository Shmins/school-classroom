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

import com.school.main.entity.Teacher;
import com.school.main.utils.TeacherModel;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    private Teacher teacher;
    @InjectMocks
    private TeacherModel teacherModel;

    @BeforeEach
    void setup() {
        teacher = teacherModel.returnExampleTeacher();
    }

    @Test
    void createTeacherAndReturnTeacher() {
        Teacher result = this.teacherRepository.save(teacher);
        teacherModel.assertFunctionTeacher(teacher, result);
    }

    @Test
    void findTeacherByIdAndReturnTeacherCorrect() {
        Optional<Teacher> result = this.teacherRepository.findById(teacher.getId());
        assertTrue(result.isPresent());
        teacherModel.assertFunctionTeacher(teacher, result.get());
    }

    @Test
    void findTeacherByIdAndReturnNull() {
        Optional<Teacher> result = this.teacherRepository.findById("");
        assertFalse(result.isPresent());
    }

    @Test
    void findTeacherByCpfAndReturnTeacherCorrect() {
        Teacher result = this.teacherRepository.findByCpf(teacher.getCpf());
        teacherModel.assertFunctionTeacher(teacher, result);
    }

    @Test
    void findTeacherByCpfAndReturnNull() {
        Teacher result = this.teacherRepository.findByCpf("");
        assertNull(result);
    }
}

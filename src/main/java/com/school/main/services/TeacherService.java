package com.school.main.services;

import java.util.List;

import com.school.main.entity.Teacher;
import com.school.main.entity.dto.TeacherDto;

public interface TeacherService {
    void createTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Teacher getStudentByCpf(String cpf);

    Teacher getStudentByName(String name);

    Teacher getStudentById(String id);

    Teacher updateTeacher(TeacherDto teacherDto);

    void deleteById(String id);

}

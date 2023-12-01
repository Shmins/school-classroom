package com.school.main.services;

import java.util.List;

import com.school.main.entity.Teacher;
import com.school.main.entity.dto.TeacherDto;

public interface TeacherService {
    Teacher createTeacher(TeacherDto teacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherByCpf(String cpf);

    Teacher getTeacherByName(String name);

    Teacher getTeacherById(String id);

    Teacher updateTeacher(TeacherDto teacherDto);

    void deleteById(String id);

}

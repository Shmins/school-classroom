package com.school.main.services;

import java.util.List;

import com.school.main.entity.dto.ClassDto;
import com.school.main.entity.Class;
import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.utils.SchoolSegment;
import com.school.main.entity.utils.Turn;
public interface ClassService {
    
    Class createClass(ClassDto data);

    Class addStudent(String id, StudentDto data);

    List<Class> getAllClass();

    Class getClassById(String id);

    Class getClassByIdOfStudent(String id);

    List<StudentDto> getAllStudentOfClass(String id);

    ClassDto getClassByIdOfTeacher(String id);

    List<Class> getClassByTurn(Turn turn);

    List<Class> getClassBySchoolSegment(SchoolSegment schoolSegment);

    ClassDto updateClass(ClassDto classDto);

    void deleteById(String id);

}

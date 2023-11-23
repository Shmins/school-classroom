package com.school.main.services;

import com.school.main.entity.Class;
import com.school.main.entity.dto.ClassDto;
public interface ClassService {
    
    Class createClass(Class data);

    Class getAllClass();

    Class getClassById(String id);

    Class getClassByIdOfStudent(String id);

    Class getClassByIdOfTeacher(String id);

    Class getClassBySchoolSegment(String schoolSegment);

    Class updateClass(ClassDto classDto);

    Class deleteById(String id);

}

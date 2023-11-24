package com.school.main.entity.dto;

import java.util.ArrayList;
import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.Teacher;
import com.school.main.entity.utils.SchoolSegment;
import com.school.main.entity.utils.Status;
import com.school.main.entity.utils.Turn;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDto {
    private String title;
    private Teacher teacherPrincipal;
    private List<Teacher> teacherAux = new ArrayList<>();
    private List<Student> studants = new ArrayList<>();
    private Status status;
    private SchoolSegment schoolSegment;
    private Turn turn;
}

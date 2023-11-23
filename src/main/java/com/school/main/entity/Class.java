package com.school.main.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.school.main.entity.utils.Status;
import com.school.main.entity.dto.ClassDto;
import com.school.main.entity.utils.SchoolSegment;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "class")
public class Class {
    @Id
    private String id;
    @NonNull
    private String title;
    @NonNull
    private Teacher teacherPrincipal;
    @NonNull
    private List<Teacher> teacherAux = new ArrayList<>();
    @NonNull
    private List<Student> studants = new ArrayList<>();
    @NonNull
    private Status status;
    @NonNull
    private SchoolSegment schoolSegment;

    public Class(ClassDto classDto){
        this.title = classDto.getTitle();
        this.teacherPrincipal = classDto.getTeacherPrincipal();
        this.teacherAux = classDto.getTeacherAux();
        this.studants = classDto.getStudants();
        this.status = classDto.getStatus();
        this.schoolSegment = classDto.getSchoolSegment();
    }
}

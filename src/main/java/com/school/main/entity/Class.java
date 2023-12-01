package com.school.main.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.school.main.entity.utils.Status;
import com.school.main.entity.utils.Turn;

import jakarta.validation.constraints.NotNull;

import com.school.main.entity.dto.ClassDto;
import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.dto.TeacherDto;
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

    @NotNull
    private String title;

    @NotNull
    private TeacherDto teacher;

    private List<StudentDto> students = new ArrayList<>();
    @NotNull
    private Status status;

    @NotNull
    private SchoolSegment schoolSegment;

    @NotNull
    private Turn turn;

    public Class(String id, @NonNull String title, @NonNull TeacherDto teacher, List<StudentDto> students, @NonNull Status status,
            @NonNull SchoolSegment schoolSegment, @NonNull Turn turn) {
        if(students == null){
            students = new ArrayList<>();
        }
        this.id = id;
        this.title = title;
        this.teacher = teacher;
        this.students = students;
        this.status = status;
        this.schoolSegment = schoolSegment;
        this.turn = turn;
    }

    public ClassDto convert() {
        return new ClassDto(this.id, this.title, this.teacher, this.students, this.status, this.schoolSegment,
                this.turn);
    }
}

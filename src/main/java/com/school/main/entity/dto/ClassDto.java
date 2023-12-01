package com.school.main.entity.dto;

import java.util.List;

import com.school.main.entity.utils.SchoolSegment;
import com.school.main.entity.utils.Status;
import com.school.main.entity.utils.Turn;
import com.school.main.entity.Class;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassDto {
    private String id;
    private String title;
    private TeacherDto teacher;
    private List<StudentDto> students;
    private Status status;
    private SchoolSegment schoolSegment;
    private Turn turn;

    public ClassDto(Class data) {
        this.id = data.getId();
        this.title = data.getTitle();
        this.teacher = data.getTeacher();
        this.students = data.getStudents();
        this.status = data.getStatus();
        this.schoolSegment = data.getSchoolSegment();
        this.turn = data.getTurn();
    }

    public Class convert() {
        return new Class(this.id, this.title, this.teacher, this.students, this.status, this.schoolSegment, this.turn);
    }
}

package com.school.main.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.dto.ClassDto;
import com.school.main.entity.dto.CoordinatorDto;
import com.school.main.entity.Class;

import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.dto.TeacherDto;
import com.school.main.entity.utils.SchoolSegment;
import com.school.main.entity.utils.Turn;
import com.school.main.services.ClassService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "classroom")
public class ClassControllers {
    private final ClassService classService;

    public ClassControllers(ClassService classService) {
        this.classService = classService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })

    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @PostMapping(value = "/", consumes = "application/json")
    @Operation(summary = "Create class using schema ClassDto and return result", tags = "Classroom")
    public ResponseEntity<ClassDto> createClass(@RequestBody ClassDto classDto) {
        return ResponseEntity.ok().body(this.classService.createClass(classDto).convert());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PostMapping(value = "/student/{id}", consumes = "application/json")
    @Operation(summary = "Create class using schema ClassDto and return result", tags = "Classroom")
    public ResponseEntity<ClassDto> addStudentInClassroom(@PathVariable String id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok().body(this.classService.addStudent(id, studentDto).convert());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Student", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CoordinatorDto.class))) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @GetMapping(value = "/getAll")
    @Operation(summary = "get all class and return list of ClassDto", tags = "Classroom")
    public ResponseEntity<List<ClassDto>> getAllClassroom() {
        List<ClassDto> list = new ArrayList<>();
        for (Class i : this.classService.getAllClass()) {
            list.add(i.convert());
        }
        return ResponseEntity.ok().body(list);
    }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @GetMapping(value = "/{id}")
    @Operation(summary = "get class and return ClassDto", tags = "Classroom")
    public ResponseEntity<ClassDto> getClassroomById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.classService.getClassById(id).convert());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @GetMapping(value = "/student/{id}")
    @Operation(summary = "get class filtered by student and return ClassDto", tags = "Classroom")
    public ResponseEntity<ClassDto> getClassroomByIdOfStudent(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.classService.getClassByIdOfStudent(id).convert());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Student", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CoordinatorDto.class))) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @GetMapping(value = "/getAll/student/{id}")
    @Operation(summary = "get class and return ClassDto", tags = "Classroom")
    public ResponseEntity<List<StudentDto>> getAllStudentOfClassroom(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.classService.getAllStudentOfClass(id));
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PreAuthorize("hasRole('ROLE_COORDINATOR') or hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/teacher/{id}")
    @Operation(summary = "get all class filtered by teacher and return list of ClassDto", tags = "Classroom")
    public ResponseEntity<ClassDto> getClassroomByIdOfTeacher(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.classService.getClassByIdOfTeacher(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Student", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CoordinatorDto.class))) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PreAuthorize("hasRole('ROLE_COORDINATOR') or hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/turn/{turn}")
    @Operation(summary = "get all class filtered by turn and return list of ClassDto", tags = "Classroom")
    public ResponseEntity<List<ClassDto>> getClassroomByTurn(@PathVariable("turn") Turn turn) {
        List<ClassDto> list = new ArrayList<>();
        for (Class i : this.classService.getClassByTurn(turn)) {
            list.add(i.convert());
        }
        return ResponseEntity.ok().body(list);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created Student", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CoordinatorDto.class))) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @GetMapping(value = "/school/{schoolSegment}")
    @Operation(summary = "get all class filtered by school segment and return list of ClassDto", tags = "Classroom")
    public ResponseEntity<List<ClassDto>> getClassroomBySchoolSegment(
            @PathVariable("schoolSegment") SchoolSegment schoolSegment) {
        List<ClassDto> list = new ArrayList<>();
        for (Class i : this.classService.getClassBySchoolSegment(schoolSegment)) {
            list.add(i.convert());
        }
        return ResponseEntity.ok().body(list);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "request OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
    @PutMapping(value = "/")
    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @Operation(summary = "Update classroom and return ClassDto changed", tags = "Classroom")
    public ResponseEntity<ClassDto> updateClassroom(ClassDto classDto) {
        return ResponseEntity.ok().body(this.classService.updateClass(classDto));
    }

    @PreAuthorize("hasRole('ROLE_COORDINATOR')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Classroom", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - coordinator only", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of Classroom not found", content = @Content) })
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete classroom and return status (200)", tags = "Classroom")
    public ResponseEntity<Void> deleteClassroombyId(@PathVariable String id) {
        this.classService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

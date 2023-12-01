package com.school.main.controllers;

import java.util.ArrayList;
import java.util.List;

import com.school.main.entity.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.dto.TeacherDto;
import com.school.main.services.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("teacher")
public class TeacherControllers {
        private final TeacherService teacherService;

        public TeacherControllers(TeacherService teacherService) {
                this.teacherService = teacherService;
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Create teacher using schema TeacherDto and return status (200)", tags = "Teacher")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Created Teacher", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @PostMapping(value = "/", consumes = "application/json")
        public ResponseEntity<TeacherDto> createTeacher(@RequestBody @Validated TeacherDto data) {
                return ResponseEntity.status(200).body(this.teacherService.createTeacher(data).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Search all teachers and return StudentDto", tags = "Teacher")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get all teachers", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/getAll")
        public ResponseEntity<List<TeacherDto>> getAll() {
                List<TeacherDto> teacherDto = new ArrayList<>();
                for(Teacher i : this.teacherService.getAllTeachers()){
                        teacherDto.add(i.convert());
                }
                return ResponseEntity.status(200).body(teacherDto);
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Search teacher by id and return TeacherDto", tags = "Teacher")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get teacher by id", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/{id}")
        public ResponseEntity<TeacherDto> getTeacherById(@PathVariable String id) {
                return ResponseEntity.status(200).body(this.teacherService.getTeacherById(id).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get teacher by cpf", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/cpf/{cpf}")
        @Operation(summary = "Search teacher by cpf and return TeacherDto", tags = "Teacher")
        public ResponseEntity<TeacherDto> getTeacherByCpf(@PathVariable String cpf) {
                return ResponseEntity.status(200).body(this.teacherService.getTeacherByCpf(cpf).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get by name", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TeacherDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/name/{name}")
        @Operation(summary = "Search teacher by name and return TeacherDto", tags = "Teacher")
        public ResponseEntity<TeacherDto> getTeacherByName(@PathVariable String name) {
                return ResponseEntity.status(200).body(this.teacherService.getTeacherByName(name).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Updated teacher", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of teacher not found", content = @Content) })
        @PutMapping(value = "/")
        @Operation(summary = "Update teacher and return status (200)", tags = "Teacher")
        public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto data) {
                return ResponseEntity.ok().body(this.teacherService.updateTeacher(data).convert());
        }

        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Delete teacher", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - coordinator only", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of teacher not found", content = @Content) })
        @DeleteMapping(value = "/{id}")
        @Operation(summary = "Delete teacher and return status (200)", tags = "Teacher")
        public ResponseEntity<Void> deleteById(@PathVariable String id) {
                this.teacherService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}

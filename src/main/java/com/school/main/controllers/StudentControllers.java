package com.school.main.controllers;

import java.util.ArrayList;
import java.util.List;

import com.school.main.entity.Student;
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
import com.school.main.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("student")
public class StudentControllers {
        private final StudentService studentService;

        public StudentControllers(StudentService studentService) {
                this.studentService = studentService;
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Create student using schema StudentDto and return status (200)", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Created Student", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @PostMapping(value = "/", consumes = "application/json")
        public ResponseEntity<StudentDto> createStudent(@RequestBody @Validated StudentDto data) {
                return ResponseEntity.status(200).body(this.studentService.createStudent(data).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Search all students and return StudentDto", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Created Student", content = {
                                        @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StudentDto.class))) }),
                        @ApiResponse(responseCode = "403", description = "Forbidden - necessary login of Coordinator", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),

                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/getAll")
        public ResponseEntity<List<StudentDto>> getAllStudents() {
                List<StudentDto> students = new ArrayList<>();
                for(Student i : this.studentService.getAllStudants()){
                        students.add(i.convert());
                }
                return ResponseEntity.status(200).body(students);
        }

        @Operation(summary = "Search student by id and return StudentDto", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get Student by id", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/{id}")
        public ResponseEntity<StudentDto> getStudentById(@PathVariable String id) {
                return ResponseEntity.status(200).body(this.studentService.getStudentById(id).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Search student by cpf and return StudentDto", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get Student by cpf", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/cpf/{cpf}")
        public ResponseEntity<StudentDto> getStudentByCpf(@PathVariable String cpf) {
                return ResponseEntity.status(200).body(this.studentService.getStudentByCpf(cpf).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Search student by name and return StudentDto", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Get Student by name", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content) })
        @GetMapping(value = "/name/{name}")
        public ResponseEntity<StudentDto> getStudentByName(@PathVariable String name) {
                return ResponseEntity.status(200).body(this.studentService.getStudentByName(name).convert());
        }
        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Update student and return StudentDto changed", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Updated Student", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = StudentDto.class)) }),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of student not found", content = @Content) })
        @PutMapping(value = "/")
        public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto data) {
                return ResponseEntity.ok().body(this.studentService.updateStudent(data).convert());
        }

        @PreAuthorize("hasRole('ROLE_COORDINATOR')")
        @Operation(summary = "Delete student and return status (200)", tags = "Student")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Delete Student", content = @Content),
                        @ApiResponse(responseCode = "401", description = "Unauthorized - Necessary login", content = @Content),
                        @ApiResponse(responseCode = "403", description = "Forbidden - coordinator only", content = @Content),
                        @ApiResponse(responseCode = "404", description = "Not found - router not found", content = @Content),
                        @ApiResponse(responseCode = "500", description = "Internal server error - possible cause: id of student not found", content = @Content) })
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable String id) {
                this.studentService.deleteById(id);
                return ResponseEntity.ok().build();
        }

}

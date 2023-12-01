package com.school.main.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.school.main.entity.Student;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.main.controllers.StudentControllers;
import com.school.main.entity.dto.StudentDto;
import com.school.main.services.StudentService;
import com.school.main.utils.StudentModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @InjectMocks
    private StudentControllers studentControllers;
    @Mock
    private StudentService studentService;

    private StudentDto studentDto;

    @BeforeEach
    void setup(){
        List<Responsible> responsibles = new ArrayList<>();
        Date date = new Date(11, 11, 2004);
        studentDto = new Student(
                "656383a2128c3f79334edeba",
                "096.879.823-30",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date,
                responsibles).convert();
    }
    @Test
    void createStudentInControllerUsingStudentDtoAndReturnResponseEntity(){
        when(studentService.createStudent(Mockito.any(StudentDto.class))).thenReturn(studentDto.convert());

        StudentDto result = studentControllers.createStudent(studentDto).getBody();

        assertNotNull(result);
        assertEquals(studentDto.getCpf(), result.getCpf());
        assertEquals(studentDto.getName(), result.getName());
        assertEquals(studentDto.getPassword(), result.getPassword());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(studentDto.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(studentDto.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(studentDto.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(studentDto.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : studentDto.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
    @Test
    void getStudentInControllerUsingPathVaribleIdAndReturnResponseEntity(){
        when(studentService.getStudentById(studentDto.getId())).thenReturn(studentDto.convert());

        StudentDto result = (StudentDto) studentControllers.getStudentById(studentDto.getId()).getBody();

        assertNotNull(result);
        assertEquals(studentDto.getCpf(), result.getCpf());
        assertEquals(studentDto.getName(), result.getName());
        assertEquals(studentDto.getPassword(), result.getPassword());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(studentDto.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(studentDto.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(studentDto.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(studentDto.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : studentDto.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
    @Test
    void getStudentInControllerUsingPathVaribleIdAndReturnNull(){
        when(studentService.getStudentById("")).thenReturn(null);

        StudentDto result = (StudentDto) studentControllers.getStudentById("").getBody();
        assertNull(result);
    }
    @Test
    void getStudentInControllerUsingPathVaribleCpfAndReturnResponseEntity(){
        when(studentService.getStudentByCpf(studentDto.getCpf())).thenReturn(studentDto.convert());

        StudentDto result = (StudentDto) studentControllers.getStudentByCpf(studentDto.getCpf()).getBody();

        assertNotNull(result);
        assertEquals(studentDto.getCpf(), result.getCpf());
        assertEquals(studentDto.getName(), result.getName());
        assertEquals(studentDto.getPassword(), result.getPassword());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(studentDto.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(studentDto.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(studentDto.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(studentDto.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : studentDto.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
    @Test
    void getStudentInControllerUsingPathVaribleCpfAndReturnNull(){
        when(studentService.getStudentByCpf("")).thenReturn(null);

        StudentDto result = (StudentDto) studentControllers.getStudentByCpf("").getBody();
        assertNull(result);
    }
    @Test
    void getStudentInControllerUsingPathVaribleNameAndReturnResponseEntity(){
        when(studentService.getStudentByName(studentDto.getName())).thenReturn(studentDto.convert());

        StudentDto result = (StudentDto) studentControllers.getStudentByName(studentDto.getName()).getBody();

        assertNotNull(result);
        assertEquals(studentDto.getCpf(), result.getCpf());
        assertEquals(studentDto.getName(), result.getName());
        assertEquals(studentDto.getPassword(), result.getPassword());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(studentDto.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(studentDto.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(studentDto.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(studentDto.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : studentDto.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
    @Test
    void getStudentInControllerUsingPathVaribleNameAndReturnNull(){
        when(studentService.getStudentByName("")).thenReturn(null);

        StudentDto result = (StudentDto) studentControllers.getStudentByName("").getBody();
        assertNull(result);
    }
    @Test
    void putStudentInControllerUsingStudentDtoAndReturnResponseEntity(){
        when(studentService.updateStudent(studentDto)).thenReturn(studentDto.convert());

        StudentDto result = (StudentDto) studentControllers.updateStudent(studentDto).getBody();

        assertNotNull(result);
        assertEquals(studentDto.getCpf(), result.getCpf());
        assertEquals(studentDto.getName(), result.getName());
        assertEquals(studentDto.getPassword(), result.getPassword());
        assertEquals(studentDto.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(studentDto.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(studentDto.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(studentDto.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(studentDto.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : studentDto.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
}

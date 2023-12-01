package com.school.main.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.main.controllers.TeacherControllers;
import com.school.main.entity.dto.TeacherDto;
import com.school.main.services.TeacherService;
import com.school.main.utils.TeacherModel;

@SpringBootTest
class TeacherControllerTest {
    @InjectMocks
    private TeacherControllers teacherControllers;
    @Mock
    private TeacherService teacherService;

    private TeacherDto teacherDto;
    @InjectMocks
    private TeacherModel teacherModel;

    @BeforeEach
    void setup(){
        teacherDto = teacherModel.returnExampleTeacher().convert();
    }
    @Test
    void createTeacherInControllerUsingTeacherDtoAndReturnResponseEntity(){
        when(teacherService.createTeacher(Mockito.any(TeacherDto.class))).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.createTeacher(teacherDto).getBody();

        assertNotNull(result);
        teacherModel.assertFunctionTeacher(result.convert(), teacherDto.convert());
    }
    @Test
    void createTeacherInControllerUsingTeacherDtoAndReturnNull(){
        when(teacherService.createTeacher(new TeacherDto(teacherDto.convert()))).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.createTeacher(teacherDto).getBody();

        assertNull(result);
    }
    @Test
    void getTeacherInControllerUsingPathVaribleIdAndReturnResponseEntity(){
        when(teacherService.getTeacherById(teacherDto.getId())).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherById(teacherDto.getId()).getBody();

        assertNotNull(result);
        teacherModel.assertFunctionTeacher(result.convert(), teacherDto.convert());
    }
    @Test
    void getTeacherInControllerUsingPathVaribleIdAndReturnNull(){
        when(teacherService.getTeacherById("")).thenReturn(null);

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherById("").getBody();
        assertNull(result);
    }
    @Test
    void getTeacherInControllerUsingPathVaribleCpfAndReturnResponseEntity(){
        when(teacherService.getTeacherByCpf(teacherDto.getCpf())).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherByCpf(teacherDto.getCpf()).getBody();

        assertNotNull(result);
        teacherModel.assertFunctionTeacher(result.convert(), teacherDto.convert());
    }
    @Test
    void getTeacherInControllerUsingPathVaribleCpfAndReturnNull(){
        when(teacherService.getTeacherByCpf("")).thenReturn(null);

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherByCpf("").getBody();
        assertNull(result);
    }
    @Test
    void getTeacherInControllerUsingPathVaribleNameAndReturnResponseEntity(){
        when(teacherService.getTeacherByName(teacherDto.getName())).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherByName(teacherDto.getName()).getBody();

        assertNotNull(result);
        teacherModel.assertFunctionTeacher(result.convert(), teacherDto.convert());
    }
    @Test
    void getTeacherInControllerUsingPathVaribleNameAndReturnNull(){
        when(teacherService.getTeacherByName("")).thenReturn(null);

        TeacherDto result = (TeacherDto) teacherControllers.getTeacherByName("").getBody();
        assertNull(result);
    }
    @Test
    void putTeacherInControllerUsingTeacherDtoAndReturnResponseEntity(){
        when(teacherService.updateTeacher(teacherDto)).thenReturn(teacherDto.convert());

        TeacherDto result = (TeacherDto) teacherControllers.updateTeacher(teacherDto).getBody();

        assertNotNull(result);
        teacherModel.assertFunctionTeacher(result.convert(), teacherDto.convert());
    }
}

package com.school.main.methods.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import com.school.main.entity.utils.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.school.main.entity.Teacher;
import com.school.main.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    private TeacherRepository teacherRepository;
    @InjectMocks
    private TeacherServiceImpl teacherServiceImpl;

    private Teacher teacher;

    @BeforeEach
    void setup() {
        Date date = new Date(11, 11, 2004);
        teacher = new Teacher(
                "656383a2128c3f79334edeba",
                "295.253.580-97",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date);
    }

    @Test
    void shouldReturnATeacherWhenSaveInDatabaseWithTeacherDto() {
        when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);

        Teacher result = teacherServiceImpl.createTeacher(teacher.convert());

        assertEquals(teacher.getCpf(),
                result.getCpf());
        assertEquals(teacher.getName(),
                result.getName());
        assertEquals(teacher.getPassword(),
                result.getPassword());
        assertEquals(teacher.getDateOfBirth(),
                result.getDateOfBirth());
        assertEquals(teacher.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(teacher.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(teacher.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(teacher.getAddress().getComplement(), result.getAddress().getComplement());
    }
    @Test
    void shouldThrowAIllegalStateExceptionWhenYouHaveACpfAlreadyRegisteredInTheDatabaseFoundWithMethodFindById(){
        when(teacherRepository.findByCpf(teacher.getCpf())).thenReturn(teacher);
        assertThrows(IllegalStateException.class, () -> teacherServiceImpl.createTeacher(teacher.convert()));
    }
    @Test
    void shouldReturnAAllWhenItExistsCoordinatorsWithAList(){
        when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(this.teacherServiceImpl.getAllTeachers());
    }
    @Test
    void getTeacherByIdAndReturnTeacherDtoCorrect() {
        Optional<Teacher> cl = Optional.of(teacher);

        when(teacherRepository.findById(teacher.getId())).thenReturn(cl);

        Teacher result = this.teacherServiceImpl.getTeacherById(teacher.getId());

        assertEquals(teacher.getCpf(),
                result.getCpf());
        assertEquals(teacher.getName(),
                result.getName());
        assertEquals(teacher.getPassword(),
                result.getPassword());
        assertEquals(teacher.getDateOfBirth(),
                result.getDateOfBirth());
        assertEquals(teacher.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(teacher.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(teacher.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(teacher.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnATeacherWhenItExistsWithCpf() {
        when(teacherRepository.findByCpf(teacher.getCpf())).thenReturn(teacher);

        Teacher result = this.teacherServiceImpl.getTeacherByCpf(teacher.getCpf());

        assertEquals(teacher.getCpf(),
                result.getCpf());
        assertEquals(teacher.getName(),
                result.getName());
        assertEquals(teacher.getPassword(),
                result.getPassword());
        assertEquals(teacher.getDateOfBirth(),
                result.getDateOfBirth());
        assertEquals(teacher.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(teacher.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(teacher.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(teacher.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnATeacherWhenItExistsWithName() {
        when(teacherRepository.findByName(teacher.getName())).thenReturn(teacher);

        Teacher result = this.teacherServiceImpl.getTeacherByName(teacher.getName());

        assertEquals(teacher.getCpf(),
                result.getCpf());
        assertEquals(teacher.getName(),
                result.getName());
        assertEquals(teacher.getPassword(),
                result.getPassword());
        assertEquals(teacher.getDateOfBirth(),
                result.getDateOfBirth());
        assertEquals(teacher.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(teacher.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(teacher.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(teacher.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnATeacherWhenChangesAreMadeToTheDatabaseWithTeacher() {
        Optional<Teacher> cl = Optional.of(teacher);

        when(teacherRepository.save(Mockito.any(Teacher.class))).thenReturn(teacher);
        when(teacherRepository.findById(teacher.getId())).thenReturn(cl);

        Teacher result = teacherServiceImpl.updateTeacher(teacher.convert());

        assertNotNull(result);
    }
    @Test
    void shouldReturnVoidThatDeleteWithId(){
        this.teacherServiceImpl.deleteById(teacher.getId());
        verify(teacherRepository).deleteById(teacher.getId());
    }
}

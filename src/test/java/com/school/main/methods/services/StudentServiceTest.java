package com.school.main.methods.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.school.main.entity.Student;
import com.school.main.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;
    private Student student;

    @BeforeEach
    void setup() {
        List<Responsible> responsibles = new ArrayList<>();
        Date date = new Date(11, 11, 2004);
        student = new Student(
                "656383a2128c3f79334edeba",
                "096.879.823-30",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date,
                responsibles);
    }

    @Test
    void shouldReturnAStudentWhenSaveStudentWithStudentDto(){
        when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        Student result = this.studentService.createStudent(student.convert());

        assertEquals(student.getCpf(), result.getCpf());
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(student.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(student.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(student.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(student.getAddress().getComplement(), result.getAddress().getComplement());
        for (int i = 0; i < student.getResponsibles().size(); ++i) {
            assertEquals(student.getResponsibles().get(i).getName(), result.getResponsibles().get(i).getName());
            assertEquals(student.getResponsibles().get(i).getEmail(), result.getResponsibles().get(i).getEmail());
            assertEquals(student.getResponsibles().get(i).getPhone(), result.getResponsibles().get(i).getPhone());
        }
    }
    @Test
    void shouldThrowAIllegalStateExceptionWhenYouHaveACpfAlreadyRegisteredInTheDatabaseFoundWithMethodFindById(){
        when(studentRepository.findByCpf(student.getCpf())).thenReturn(student);
        assertThrows(IllegalStateException.class, () -> studentService.createStudent(student.convert()));
    }
    @Test
    void shouldReturnAStudentWhenItExistsWithId() {
        Optional<Student> cl = Optional.of(student);

        when(studentRepository.findById(student.getId())).thenReturn(cl);

        Student result = this.studentService.getStudentById(student.getId());

        assertEquals(student.getCpf(), result.getCpf());
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(student.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(student.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(student.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(student.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : student.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }
    @Test
    void shouldReturnAAllWhenItExistsCoordinatorsWithAList(){
        when(studentRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(this.studentService.getAllStudants());
    }
    @Test
    void shouldReturnAStudentWhenItExistsWithCpf() {
        when(studentRepository.findByCpf(student.getCpf())).thenReturn(student);

        Student result = this.studentService.getStudentByCpf(student.getCpf());

        assertEquals(student.getCpf(), result.getCpf());
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(student.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(student.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(student.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(student.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : student.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }

    @Test
    void shouldReturnAStudentWhenItExistsWithName() {
        when(studentRepository.findByName(student.getName())).thenReturn(student);

        Student result = this.studentService.getStudentByName(student.getName());

        assertEquals(student.getCpf(), result.getCpf());
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getPassword(), result.getPassword());
        assertEquals(student.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(student.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(student.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(student.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(student.getAddress().getComplement(), result.getAddress().getComplement());
        for (Responsible i : student.getResponsibles()) {
            assertEquals(i.getName(), i.getName());
            assertEquals(i.getEmail(), i.getEmail());
            assertEquals(i.getPhone(), i.getPhone());
        }
    }

    @Test
    void shouldReturnAStudentWhenChangesAreMadeToTheDatabaseWithStudent() {
        Optional<Student> cl = Optional.of(student);

        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        when(studentRepository.findById(student.getId())).thenReturn(cl);

        Student result = studentService.updateStudent(student.convert());

        assertNotNull(result);
    }
    @Test
    void shouldReturnVoidThatDeleteWithId(){
        this.studentService.deleteById(student.getId());
        verify(studentRepository).deleteById(student.getId());
    }
}

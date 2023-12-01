package com.school.main.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.school.main.entity.Student;
import com.school.main.entity.utils.Address;
import com.school.main.entity.utils.Responsible;

public class StudentModel {
    public Student returnExampleStudent(){
        List<Responsible> responsibles = new ArrayList<>();
        Date date = new Date(11, 11, 2004);
        return new Student(
                "656383a2128c3f79334edeba",
                "096.879.823-30",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date,
                responsibles);
    }
    public void assertFunctionStudent(Student expected, Student returnValue) {
        assertEquals(expected.getCpf(), returnValue.getCpf());
        assertEquals(expected.getName(), returnValue.getName());
        assertEquals(expected.getPassword(), returnValue.getPassword());
        assertEquals(expected.getDateOfBirth(), returnValue.getDateOfBirth());
        compareAddress(expected.getAddress(), returnValue.getAddress());
        compareResponsible(expected.getResponsibles(), returnValue.getResponsibles());
    }

    private void compareAddress(Address expected, Address returnValue) {
        assertEquals(expected.getRoad(), returnValue.getRoad());
        assertEquals(expected.getDistrict(), returnValue.getDistrict());
        assertEquals(expected.getNumber(), returnValue.getNumber());
        assertEquals(expected.getComplement(), returnValue.getComplement());
    }

    private void compareResponsible(List<Responsible> expected, List<Responsible> returnValue) {
        for (int i = 0; expected.size() > i; ++i) {
            assertEquals(expected.get(i).getName(), returnValue.get(i).getName());
            assertEquals(expected.get(i).getEmail(), returnValue.get(i).getEmail());
            assertEquals(expected.get(i).getPhone(), returnValue.get(i).getPhone());
        }
    }
}

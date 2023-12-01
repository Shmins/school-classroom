package com.school.main.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import com.school.main.entity.Teacher;
import com.school.main.entity.utils.Address;

public class TeacherModel {
    public Teacher returnExampleTeacher(){
        Date date = new Date(11, 11, 2004);
        return new Teacher(
                "656383a2128c3f79334edeba",
                "295.253.580-97",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date);
    }
    public void assertFunctionTeacher(Teacher expected, Teacher returnValue) {
        assertEquals(expected.getCpf(), returnValue.getCpf());
        assertEquals(expected.getName(), returnValue.getName());
        assertEquals(expected.getPassword(), returnValue.getPassword());
        assertEquals(expected.getDateOfBirth(), returnValue.getDateOfBirth());
        compareAddress(expected.getAddress(), returnValue.getAddress());
    }

    private void compareAddress(Address expected, Address returnValue) {
        assertEquals(expected.getRoad(), returnValue.getRoad());
        assertEquals(expected.getDistrict(), returnValue.getDistrict());
        assertEquals(expected.getNumber(), returnValue.getNumber());
        assertEquals(expected.getComplement(), returnValue.getComplement());
    }
}

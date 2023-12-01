package com.school.main.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.school.main.entity.Coordinator;
import com.school.main.entity.utils.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.school.main.controllers.CoordinatorControllers;
import com.school.main.entity.dto.CoordinatorDto;
import com.school.main.services.CoordinatorService;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
class CoordinatorControllerTest {
    @InjectMocks
    private CoordinatorControllers coordinatorControllers;
    @Mock
    private CoordinatorService coordinatorService;

    private CoordinatorDto coordinatorDto;

    @BeforeEach
    void setup() {
        Date date = new Date(11, 11, 2004);
        coordinatorDto = new Coordinator(
                "656383a2128c3f79334edeba",
                "623.385.350-81",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date).convert();
    }

    @Test
    void shouldReturnCoordinatorDtoInResponseEntityWhenSaveInDatabaseWithCoordinatorDto() {
        when(coordinatorService.createCoordinator(Mockito.any(CoordinatorDto.class))).thenReturn(coordinatorDto.convert());

        ResponseEntity<CoordinatorDto> result = coordinatorControllers.createCoordinator(coordinatorDto);

        assertNotNull(result.getBody());
        assertEquals(coordinatorDto.getCpf(), result.getBody().getCpf());
        assertEquals(coordinatorDto.getName(), result.getBody().getName());
        assertEquals(coordinatorDto.getPassword(), result.getBody().getPassword());
        assertEquals(coordinatorDto.getDateOfBirth(), result.getBody().getDateOfBirth());
        assertEquals(coordinatorDto.getAddress().getRoad(), result.getBody().getAddress().getRoad());
        assertEquals(coordinatorDto.getAddress().getDistrict(), result.getBody().getAddress().getDistrict());
        assertEquals(coordinatorDto.getAddress().getNumber(), result.getBody().getAddress().getNumber());
        assertEquals(coordinatorDto.getAddress().getComplement(), result.getBody().getAddress().getComplement());
    }

    @Test
    void shouldReturnCoordinatorDtoInResponseEntityWhenIsExistsWithId() {
        when(coordinatorService.getCoordinatorById(Mockito.any())).thenReturn(coordinatorDto.convert());

        ResponseEntity<CoordinatorDto> result = coordinatorControllers.getCoordinatorById(coordinatorDto.getId());
        assertNotNull(result.getBody());
        assertEquals(coordinatorDto.getCpf(), result.getBody().getCpf());
        assertEquals(coordinatorDto.getName(), result.getBody().getName());
        assertEquals(coordinatorDto.getPassword(), result.getBody().getPassword());
        assertEquals(coordinatorDto.getDateOfBirth(), result.getBody().getDateOfBirth());
        assertEquals(coordinatorDto.getAddress().getRoad(), result.getBody().getAddress().getRoad());
        assertEquals(coordinatorDto.getAddress().getDistrict(), result.getBody().getAddress().getDistrict());
        assertEquals(coordinatorDto.getAddress().getNumber(), result.getBody().getAddress().getNumber());
        assertEquals(coordinatorDto.getAddress().getComplement(), result.getBody().getAddress().getComplement());
    }

    @Test
    void shouldReturnCoordinatorDtoInResponseEntityWhenIsExistsWithCpf() {
        when(coordinatorService.getCoordinatorByCpf(coordinatorDto.getCpf())).thenReturn(coordinatorDto.convert());

        ResponseEntity<CoordinatorDto> result = coordinatorControllers.getCoordinatorByCpf(coordinatorDto.getCpf());

        assertNotNull(result.getBody());
        assertEquals(coordinatorDto.getCpf(), result.getBody().getCpf());
        assertEquals(coordinatorDto.getName(), result.getBody().getName());
        assertEquals(coordinatorDto.getPassword(), result.getBody().getPassword());
        assertEquals(coordinatorDto.getDateOfBirth(), result.getBody().getDateOfBirth());
        assertEquals(coordinatorDto.getAddress().getRoad(), result.getBody().getAddress().getRoad());
        assertEquals(coordinatorDto.getAddress().getDistrict(), result.getBody().getAddress().getDistrict());
        assertEquals(coordinatorDto.getAddress().getNumber(), result.getBody().getAddress().getNumber());
        assertEquals(coordinatorDto.getAddress().getComplement(), result.getBody().getAddress().getComplement());
    }

    @Test
    void shouldReturnCoordinatorDtoInResponseEntityWhenIsExistsWithName() {
        when(coordinatorService.getCoordinatorByName(coordinatorDto.getName())).thenReturn(coordinatorDto.convert());

        ResponseEntity<CoordinatorDto> result = coordinatorControllers.getCoordinatorByName(coordinatorDto.getName());

        assertNotNull(result.getBody());
        assertEquals(coordinatorDto.getCpf(), result.getBody().getCpf());
        assertEquals(coordinatorDto.getName(), result.getBody().getName());
        assertEquals(coordinatorDto.getPassword(), result.getBody().getPassword());
        assertEquals(coordinatorDto.getDateOfBirth(), result.getBody().getDateOfBirth());
        assertEquals(coordinatorDto.getAddress().getRoad(), result.getBody().getAddress().getRoad());
        assertEquals(coordinatorDto.getAddress().getDistrict(), result.getBody().getAddress().getDistrict());
        assertEquals(coordinatorDto.getAddress().getNumber(), result.getBody().getAddress().getNumber());
        assertEquals(coordinatorDto.getAddress().getComplement(), result.getBody().getAddress().getComplement());
    }

    @Test
    void shouldReturnACoordinatorWhenChangesAreMadeToTheDatabaseWithCoordinator() {
        when(coordinatorService.updateCoordinator(Mockito.any(CoordinatorDto.class))).thenReturn(coordinatorDto.convert());

        ResponseEntity<CoordinatorDto> result = coordinatorControllers.updateCoordinator(coordinatorDto);

        assertNotNull(result.getBody());
        assertEquals(coordinatorDto.getCpf(), result.getBody().getCpf());
        assertEquals(coordinatorDto.getName(), result.getBody().getName());
        assertEquals(coordinatorDto.getPassword(), result.getBody().getPassword());
        assertEquals(coordinatorDto.getDateOfBirth(), result.getBody().getDateOfBirth());
        assertEquals(coordinatorDto.getAddress().getRoad(), result.getBody().getAddress().getRoad());
        assertEquals(coordinatorDto.getAddress().getDistrict(), result.getBody().getAddress().getDistrict());
        assertEquals(coordinatorDto.getAddress().getNumber(), result.getBody().getAddress().getNumber());
        assertEquals(coordinatorDto.getAddress().getComplement(), result.getBody().getAddress().getComplement());
    }

    @Test
    void shouldReturnVoidThatDeleteWithId() {
        doNothing().when(this.coordinatorService).deleteById(coordinatorDto.getId());

        ResponseEntity<Void> result = coordinatorControllers.deleteById(coordinatorDto.getId());

        verify(coordinatorService).deleteById(coordinatorDto.getId());
    }
    //Mockito.doThrow(IllegalAccessError.class).when(coordinatorService).updateCoordinator(coordinatorDto);
    //        assertThrows(IllegalAccessError.class, () -> coordinatorControllers.updateCoordinator(coordinatorDto));
}

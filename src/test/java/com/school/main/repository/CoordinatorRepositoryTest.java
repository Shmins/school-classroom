package com.school.main.repository;

import java.util.Date;
import java.util.Optional;

import com.school.main.entity.utils.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.school.main.entity.Coordinator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoordinatorRepositoryTest {
    @Mock
    private CoordinatorRepository coordinatorRepository;

    private Coordinator coordinator;

    @BeforeEach
    void setup() {
        Date date = new Date(11, 11, 2004);
        coordinator = new Coordinator(
                "656383a2128c3f79334edeba",
                "623.385.350-81",
                "Pedro Alyson",
                "123456789",
                new Address(),
                date);
    }

    @Test
    void shouldReturnACoordinatorWhenSaveInDatabaseWithCoordinatorClass() {
        when(this.coordinatorRepository.save(coordinator)).thenReturn(coordinator);
        Coordinator result = this.coordinatorRepository.save(coordinator);

        assertEquals(coordinator.getCpf(), result.getCpf());
        assertEquals(coordinator.getName(), result.getName());
        assertEquals(coordinator.getPassword(), result.getPassword());
        assertEquals(coordinator.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(coordinator.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(coordinator.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(coordinator.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(coordinator.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnACoordinatorWhenIsExistsWithId() {
        when(this.coordinatorRepository.findById(coordinator.getId())).thenReturn(Optional.of(coordinator));
        Optional<Coordinator> result = this.coordinatorRepository.findById(coordinator.getId());

        assertEquals(true, result.isPresent());
        assertEquals(coordinator.getCpf(), result.get().getCpf());
        assertEquals(coordinator.getName(), result.get().getName());
        assertEquals(coordinator.getPassword(), result.get().getPassword());
        assertEquals(coordinator.getDateOfBirth(), result.get().getDateOfBirth());
        assertEquals(coordinator.getAddress().getRoad(), result.get().getAddress().getRoad());
        assertEquals(coordinator.getAddress().getDistrict(), result.get().getAddress().getDistrict());
        assertEquals(coordinator.getAddress().getNumber(), result.get().getAddress().getNumber());
        assertEquals(coordinator.getAddress().getComplement(), result.get().getAddress().getComplement());
    }
    @Test
    void shouldReturnNullWhenIsNotExistsWithId() {
        Optional<Coordinator> result = this.coordinatorRepository.findById(Mockito.any());
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnACoordinatorWhenIsExistsWithCpf() {
        when(this.coordinatorRepository.findByCpf(coordinator.getCpf())).thenReturn(coordinator);
        Coordinator result = this.coordinatorRepository.findByCpf(coordinator.getCpf());

        assertEquals(coordinator.getCpf(), result.getCpf());
        assertEquals(coordinator.getName(), result.getName());
        assertEquals(coordinator.getPassword(), result.getPassword());
        assertEquals(coordinator.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(coordinator.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(coordinator.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(coordinator.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(coordinator.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnNullWhenIsNotExistsWithCpf() {
        Coordinator result = this.coordinatorRepository.findByCpf(Mockito.any());
        assertNull(result);
    }
    @Test
    void shouldReturnACoordinatorWhenIsExistsWithName() {
        when(this.coordinatorRepository.findByName(coordinator.getName())).thenReturn(coordinator);
        Coordinator result = this.coordinatorRepository.findByName(coordinator.getName());

        assertEquals(coordinator.getCpf(), result.getCpf());
        assertEquals(coordinator.getName(), result.getName());
        assertEquals(coordinator.getPassword(), result.getPassword());
        assertEquals(coordinator.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(coordinator.getAddress().getRoad(), result.getAddress().getRoad());
        assertEquals(coordinator.getAddress().getDistrict(), result.getAddress().getDistrict());
        assertEquals(coordinator.getAddress().getNumber(), result.getAddress().getNumber());
        assertEquals(coordinator.getAddress().getComplement(), result.getAddress().getComplement());
    }

    @Test
    void shouldReturnNullWhenIsNotExistsWithName() {
        Coordinator result = this.coordinatorRepository.findByName(Mockito.any());
        assertNull(result);
    }
}

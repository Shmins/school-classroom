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

import com.school.main.entity.Coordinator;
import com.school.main.repository.CoordinatorRepository;

@ExtendWith(MockitoExtension.class)
class CoordinatorServiceTest {
    @Mock
    private CoordinatorRepository coordinatorRepository;
    @InjectMocks
    private CoordinatorServiceimpl coordinatorServiceimpl;
    
    private Coordinator coordinator;

    @BeforeEach
    void setup(){
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
    void shouldReturnACoordinatorWhenSaveCoordinatorWithCoordinatorDto(){
        when(coordinatorRepository.save(Mockito.any(Coordinator.class))).thenReturn(coordinator);

        Coordinator result = coordinatorServiceimpl.createCoordinator(coordinator.convert());
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
    void shouldThrowAIllegalStateExceptionWhenYouHaveACpfAlreadyRegisteredInTheDatabaseFoundWithMethodFindById(){
        when(coordinatorRepository.findByCpf(coordinator.getCpf())).thenReturn(coordinator);
        assertThrows(IllegalStateException.class, () -> coordinatorServiceimpl.createCoordinator(coordinator.convert()));
    }
    @Test
    void shouldReturnAAllWhenItExistsCoordinatorsWithAList(){
        when(coordinatorRepository.findAll()).thenReturn(new ArrayList<>());
        assertNotNull(this.coordinatorServiceimpl.getAllCoordinators());
        //Fixme add Coordinator
    }
    @Test
    void shouldReturnACoordinatorWhenItExistsWithId() {
        Optional<Coordinator> cl = Optional.of(coordinator);

        when(coordinatorRepository.findById(coordinator.getId())).thenReturn(cl);

        Coordinator result = this.coordinatorServiceimpl.getCoordinatorById(coordinator.getId());

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
    void shouldReturnANullWhenItNotExistsWithId() {

        when(coordinatorRepository.findById(Mockito.any())).thenReturn(Optional.of(null));

        assertThrows(NullPointerException.class, () -> coordinatorServiceimpl.getCoordinatorById(coordinator.getId()));
//Fixme trocar para Notfound
    }

    @Test
    void shouldReturnACoordinatorWhenItExistsWithCpf() {
        when(coordinatorRepository.findByCpf(coordinator.getCpf())).thenReturn(coordinator);

        Coordinator result = this.coordinatorServiceimpl.getCoordinatorByCpf(coordinator.getCpf());

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
    void shouldReturnACoordinatorWhenItExistsWithName() {
        when(coordinatorRepository.findByName(coordinator.getName())).thenReturn(coordinator);

        Coordinator result = this.coordinatorServiceimpl.getCoordinatorByName(coordinator.getName());

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
    void shouldReturnACoordinatorWhenChangesAreMadeToTheDatabaseWithCoordinator(){
        Optional<Coordinator> cl = Optional.of(coordinator);

        when(coordinatorRepository.save(Mockito.any(Coordinator.class))).thenReturn(coordinator);
        when(coordinatorRepository.findById(coordinator.getId())).thenReturn(cl);

        Coordinator result = coordinatorServiceimpl.updateCoordinator(coordinator.convert());
        assertNotNull(result);
    }

    @Test
    void shouldReturnVoidThatDeleteWithId(){
        this.coordinatorServiceimpl.deleteById(coordinator.getId());
        verify(coordinatorRepository).deleteById(coordinator.getId());
    }
}

package com.school.main.services;

import java.util.List;

import com.school.main.entity.Coordinator;
import com.school.main.entity.dto.CoordinatorDto;


public interface CoordinatorService {
    Coordinator createCoordinator(CoordinatorDto coordinatorDto);

    List<Coordinator> getAllCoordinators();

    Coordinator getCoordinatorByCpf(String cpf);

    Coordinator getCoordinatorByName(String name);

    Coordinator getCoordinatorById(String id);

    Coordinator updateCoordinator(CoordinatorDto coordinatorDto);

    void deleteById(String id);

}

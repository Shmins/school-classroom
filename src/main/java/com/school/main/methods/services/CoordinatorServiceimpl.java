package com.school.main.methods.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.main.entity.Coordinator;
import com.school.main.entity.dto.CoordinatorDto;
import com.school.main.repository.CoordinatorRepository;
import com.school.main.services.CoordinatorService;

@Service
public class CoordinatorServiceimpl implements CoordinatorService {
    private final CoordinatorRepository coordinatorRepository;

    public CoordinatorServiceimpl(CoordinatorRepository coordinatorRepository) {
        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    public Coordinator createCoordinator(CoordinatorDto coordinatorDto) {
        this.unique(coordinatorDto.getCpf());
        Coordinator coordinator = new Coordinator(coordinatorDto.getId(), coordinatorDto.getCpf(), coordinatorDto.getName(), new BCryptPasswordEncoder().encode(coordinatorDto.getPassword()), coordinatorDto.getAddress(), coordinatorDto.getDateOfBirth());
        return this.coordinatorRepository.save(coordinator);
    }

    @Override
    public List<Coordinator> getAllCoordinators() {
        return this.coordinatorRepository.findAll();
    }

    @Override
    public Coordinator getCoordinatorByCpf(String cpf) {
        return this.coordinatorRepository.findByCpf(cpf);
    }

    @Override
    public Coordinator getCoordinatorByName(String name) {
        return this.coordinatorRepository.findByName(name);
    }

    @Override
    public Coordinator getCoordinatorById(String id) {
        Optional<Coordinator> coordinator = this.coordinatorRepository.findById(id);
        return coordinator.orElse(null);
        //Fixme tratar o retorno para status não encontrado
    }

    @Override
    @Transactional
    public Coordinator updateCoordinator(CoordinatorDto data) {
        Coordinator coordinator = this.getCoordinatorById(data.getId());
        if (data.getCpf() != null) {
            this.unique(data.getCpf());
        }
        coordinator.setCpf(data.getCpf() != null ? data.getCpf() : coordinator.getCpf());
        coordinator.setName(data.getName() != null ? data.getName() : coordinator.getName());
        coordinator.setPassword(data.getCpf() != null ? data.getPassword() : coordinator.getPassword());
        coordinator.setAddress(data.getAddress() != null ? data.getAddress() : coordinator.getAddress());
        coordinator.setDateOfBirth(data.getDateOfBirth() != null ? data.getDateOfBirth() : coordinator.getDateOfBirth());
        this.coordinatorRepository.save(coordinator);
        return coordinator;
    }

    @Override
    public void deleteById(String id) {
        this.coordinatorRepository.deleteById(id);
    }

    private void unique(String cpf) {
        if (this.coordinatorRepository.findByCpf(cpf) != null) {
            throw new IllegalStateException("dados dublicados");
        }
    }
}

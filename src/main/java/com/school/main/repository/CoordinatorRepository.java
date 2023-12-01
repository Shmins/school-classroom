package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.school.main.entity.Coordinator;

public interface CoordinatorRepository extends MongoRepository<Coordinator, String> {
    @Query(value = "{'cpf': ?0}")
    Coordinator findByCpf(String cpf);
    @Query(value = "{'name': ?0}")
    Coordinator findByName(String name);
}

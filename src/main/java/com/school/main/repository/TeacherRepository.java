package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.school.main.entity.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String>{
    @Query(value = "{'cpf': ?0}")
    Teacher findByCpf(String cpf);
    @Query(value = "{'name': ?0}")
    Teacher findByName(String name);
}

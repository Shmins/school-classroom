package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.school.main.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
    @Query(value = "{'cpf': ?0}")
    Student findByCpf(String cpf);

    @Query(value = "{'name': ?0}")
    Student findByName(String name);
}

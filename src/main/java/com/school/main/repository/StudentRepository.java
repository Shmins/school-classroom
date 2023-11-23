package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.school.main.entity.Student;

public interface StudentRepository extends MongoRepository<Student, String>{
    Student findByCpf(String cpf);
}

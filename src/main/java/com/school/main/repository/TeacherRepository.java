package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.school.main.entity.Teacher;

public interface TeacherRepository extends MongoRepository<Teacher, String>{
    
}

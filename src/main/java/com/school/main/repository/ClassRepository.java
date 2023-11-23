package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.school.main.entity.Class;

public interface ClassRepository extends MongoRepository<Class, String>{
    
}

package com.school.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.school.main.entity.Class;
import java.util.List;
import com.school.main.entity.utils.Turn;
import com.school.main.entity.utils.SchoolSegment;



public interface ClassRepository extends MongoRepository<Class, String>{
    List<Class> findByTurn(Turn turn);
    Class findByTitle(String title);
    List<Class> findBySchoolSegment(SchoolSegment schoolSegment);
    @Query(value = "{'teacher.id': ?0}")
    Class findByIdTeacher(String id);
    @Query(value = "{'students.id': ?0}")
    Class findByIdStudent(String id);
}

package com.school.main.methods.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.main.entity.Class;
import com.school.main.entity.dto.ClassDto;
import com.school.main.entity.dto.StudentDto;
import com.school.main.entity.utils.SchoolSegment;
import com.school.main.entity.utils.Turn;
import com.school.main.repository.ClassRepository;
import com.school.main.services.ClassService;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;
    public ClassServiceImpl(ClassRepository classRepository){
        this.classRepository = classRepository;
    }
    @Override
    public Class createClass(ClassDto data) {
        Class result = data.convert();

        this.verifyTeacherInClassroom(result.getId());

        return this.classRepository.save(result);
    }

    @Override
    public List<Class> getAllClass() {

        return this.classRepository.findAll();
    }

    @Override
    public Class getClassById(String id) {
        Optional<Class> result = this.classRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public List<StudentDto> getAllStudentOfClass(String id) {
        Optional<Class> result = this.classRepository.findById(id);

        return result.isPresent() ? result.get().getStudents() : null;
    }

    @Override
    public Class getClassByIdOfStudent(String id) {
        return this.classRepository.findByIdStudent(id);
    }

    @Override
    public ClassDto getClassByIdOfTeacher(String id) {
        return this.classRepository.findByIdTeacher(id).convert();
    }

    @Override
    public List<Class> getClassByTurn(Turn turn) {
        return this.classRepository.findByTurn(turn);
    }

    @Override
    public List<Class> getClassBySchoolSegment(SchoolSegment schoolSegment) {
        return this.classRepository.findBySchoolSegment(schoolSegment);
    }

    @Override
    @Transactional
    public ClassDto updateClass(ClassDto classDto) {
        Class update = this.getClassById(classDto.getId());

        update.setTitle(classDto.getTitle() != null ? classDto.getTitle() : update.getTitle());
        update.setTeacher(classDto.getTeacher() != null ? classDto.getTeacher() : update.getTeacher());
        update.setStatus(classDto.getStatus() != null ? classDto.getStatus() : update.getStatus());
        update.setSchoolSegment(classDto.getSchoolSegment() != null ? classDto.getSchoolSegment()
                : update.getSchoolSegment());
        update.setTurn(classDto.getTurn() != null ? classDto.getTurn() : update.getTurn());

        return update.convert();
    }

    @Override
    public void deleteById(String id) {
        this.classRepository.deleteById(id);
    }

    @Override
    public Class addStudent(String id, StudentDto data) {
        verifyStudentTurn(data.getId());

        Class result = this.getClassById(id);

        result.getStudents().add(data);

        this.classRepository.save(result);

        return result;
    }

    private void verifyTeacherInClassroom(String id) {
        if (this.classRepository.findByIdTeacher(id) != null) {
            throw new IllegalArgumentException("Professor já está em uma sala");
        }
    }

    private void verifyStudentTurn(String id) {
        if (this.classRepository.findByIdStudent(id) != null) {
            throw new IllegalArgumentException("Aluno já está em uma sala");
        }
    }

}

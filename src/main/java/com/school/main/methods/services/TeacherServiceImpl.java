package com.school.main.methods.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.school.main.entity.Teacher;
import com.school.main.entity.dto.TeacherDto;
import com.school.main.repository.TeacherRepository;
import com.school.main.services.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher createTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher(
                teacherDto.getId(),
                teacherDto.getCpf(),
                teacherDto.getName(),
                new BCryptPasswordEncoder().encode(teacherDto.getPassword()),
                teacherDto.getAddress(),
                teacherDto.getDateOfBirth());
        this.unique(teacher.getCpf());
        return this.teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherByCpf(String cpf) {
        return this.teacherRepository.findByCpf(cpf);
    }

    @Override
    public Teacher getTeacherByName(String name) {
        return this.teacherRepository.findByName(name);
    }

    @Override
    public Teacher getTeacherById(String id) {
        Optional<Teacher> student = this.teacherRepository.findById(id);
        return student.orElse(null);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(TeacherDto data) {
        Teacher teacher = this.getTeacherById(data.getId());
        if (data.getCpf() != null) {
            this.unique(data.getCpf());
        }
        teacher.setCpf(data.getCpf() != null ? data.getCpf() : teacher.getCpf());
        teacher.setName(data.getName() != null ? data.getName() : teacher.getName());
        teacher.setPassword(data.getCpf() != null ? data.getPassword() : teacher.getPassword());
        teacher.setAddress(data.getAddress() != null ? data.getAddress() : teacher.getAddress());
        teacher.setDateOfBirth(data.getDateOfBirth() != null ? data.getDateOfBirth() : teacher.getDateOfBirth());
        this.teacherRepository.save(teacher);
        return teacher;

    }

    @Override
    public void deleteById(String id) {
        this.teacherRepository.deleteById(id);
    }

    private void unique(String cpf) {
        if (this.teacherRepository.findByCpf(cpf) != null) {
            throw new IllegalStateException("dados dublicados");
        }
    }
}

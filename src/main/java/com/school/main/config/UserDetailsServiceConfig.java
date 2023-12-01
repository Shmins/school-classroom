package com.school.main.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.main.repository.CoordinatorRepository;
import com.school.main.repository.StudentRepository;
import com.school.main.repository.TeacherRepository;

@Service
public class UserDetailsServiceConfig implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final CoordinatorRepository coordinatorRepository;

    
    public UserDetailsServiceConfig(StudentRepository studentRepository, TeacherRepository teacherRepository, CoordinatorRepository coordinatorRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usarname) throws UsernameNotFoundException {
        UserDetails student = this.studentRepository.findByCpf(usarname);
        if (student == null) {
            UserDetails teacher = this.teacherRepository.findByCpf(usarname);
            if(teacher == null){
                return this.coordinatorRepository.findByCpf(usarname);
            }
            return teacher;
        } else {
            return student;
        }
    }

}

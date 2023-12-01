package com.school.main.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.main.repository.CoordinatorRepository;
import com.school.main.repository.StudentRepository;
import com.school.main.repository.TeacherRepository;
import com.school.main.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterUser extends OncePerRequestFilter {
    private final TokenService tokenService;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final CoordinatorRepository coordinatorRepository;

    public FilterUser(TokenService tokenService, StudentRepository studentRepository,
            TeacherRepository teacherRepository, CoordinatorRepository coordinatorRepository) {

        this.tokenService = tokenService;

        this.studentRepository = studentRepository;

        this.teacherRepository = teacherRepository;

        this.coordinatorRepository = coordinatorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            String token = authorization.replace("Bearer", "");

            String cpf = tokenService.getSubject(token);

            String role = tokenService.getIssuer(token);

            switch (role) {
                case "ROLE_STUDENT": {
                    UserDetails student = this.studentRepository.findByCpf(cpf);

                    var auth = new UsernamePasswordAuthenticationToken(student, null, student.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    break;
                }
                case "ROLE_TEACHER": {
                    UserDetails teacher = this.teacherRepository.findByCpf(cpf);

                    var auth = new UsernamePasswordAuthenticationToken(teacher, null, teacher.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    break;
                }
                case "ROLE_COORDINATOR": {
                    UserDetails coordinator = this.coordinatorRepository.findByCpf(cpf);
                    
                    var auth = new UsernamePasswordAuthenticationToken(coordinator, null, coordinator.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);

                    break;
                }
                default:
                    break;
            }
            response.setHeader("Authorization", token);
        }
        filterChain.doFilter(request, response);
    }
}

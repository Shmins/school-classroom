package com.school.main.methods.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.school.main.entity.User;
import com.school.main.entity.utils.Login;
import com.school.main.entity.utils.Token;
import com.school.main.services.LoginService;
import com.school.main.services.TokenService;

@Service
public class LoginServiceImpl implements LoginService {
    private final TokenService tokenService;
    private AuthenticationManager authenticationManager;

    public LoginServiceImpl(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(Login login) {
        var userAuthentication = new UsernamePasswordAuthenticationToken(
                login.cpf(), login.password());

        var aut = this.authenticationManager.authenticate(userAuthentication);
        var user = (User) aut.getPrincipal();

        return tokenService.tokenCreate(new Token(user.getUsername(), user.getName(), user.getRole()));
    }

}

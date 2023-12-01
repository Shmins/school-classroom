package com.school.main.services;

import com.school.main.entity.utils.Token;

public interface TokenService {

    String tokenCreate(Token user);

    String getSubject(String token);

    String getIssuer(String token);
}

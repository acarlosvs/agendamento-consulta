package com.conexa.desafio.conexadesafioantoniocarlos.domain.services;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String generateToken();

    Claims decodeToken(String authorization);

    String getPasswordEncoder(String senha);
}

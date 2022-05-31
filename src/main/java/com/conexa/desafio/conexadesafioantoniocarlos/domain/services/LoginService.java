package com.conexa.desafio.conexadesafioantoniocarlos.domain.services;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.LoginRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.LoginResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Login;

public interface LoginService {
    LoginResponseDTO logar(LoginRequestDTO loginDto);
    void deslogar(String authorization);

    Login recuperarPorToken(String authorization);

    boolean validarToken(String authorization);
}

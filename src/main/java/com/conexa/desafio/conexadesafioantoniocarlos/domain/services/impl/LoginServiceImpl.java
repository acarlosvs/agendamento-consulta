package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.LoginRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.LoginResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Login;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.enums.StatusLogin;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.NaoEncontradoException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.SenhaDivergenteException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.TokenExpiradoException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.TokenInvalidoException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.LoginRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.JwtService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.LoginService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final String TOKEN_BEARER = "Bearer";

    private final MedicoService medicoService;

    private final JwtService jwtService;

    private final LoginRepository loginRepository;

    @Override
    public LoginResponseDTO logar(LoginRequestDTO loginDto) {
        Medico medico = medicoService.recuperarPorEmail(loginDto.getEmail())
                .orElseThrow(() -> new NaoEncontradoException("Nenhum cadastro encontrado"));

        if (!this.validarSenha(loginDto.getSenha(), medico.getSenha()))
            throw new SenhaDivergenteException();

        Login login = Login.builder()
                .token(jwtService.generateToken())
                .status(StatusLogin.ATIVO)
                .medico(medico)
                .build();

        login = loginRepository.save(login);

        return LoginResponseDTO.builder()
                .token(login.getToken())
                .build();
    }

    @Override
    public void deslogar(String authorization) {
        String tokenTratado = authorization.replace(TOKEN_BEARER, "").trim();

        Login login = loginRepository.findByToken(tokenTratado);
        login.setStatus(StatusLogin.INATIVO);

        loginRepository.save(login);
    }

    protected boolean validarSenha(String senhaDigitada, String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, senha);
    }

    @Override
    public Login recuperarPorToken(String authorization) {
        return loginRepository.findByToken(authorization);
    }

    @Override
    public boolean validarToken(String authorization) {
        String tokenTratado = authorization.replace(TOKEN_BEARER,"").trim();

        Claims claims = jwtService.decodeToken(tokenTratado);
        if (claims.getExpiration().before(new Date(System.currentTimeMillis())))
            throw new TokenExpiradoException();

        if (this.recuperarPorToken(authorization).getStatus() == StatusLogin.INATIVO)
            throw new TokenInvalidoException("Token invalido.");

        return true;
    }
}

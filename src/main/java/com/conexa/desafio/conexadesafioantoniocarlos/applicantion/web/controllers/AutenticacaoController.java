package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.LoginRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.LoginResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.consts.RoutePaths.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_BASE_PATH + API_VERSION_PATH)
public class AutenticacaoController {

    private final LoginService loginService;

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginDTO) {
        return ResponseEntity.ok(loginService.logar(loginDTO));
    }

    @PostMapping(LOGOFF_PATH)
    public ResponseEntity<Void> logoff(@RequestHeader @NotBlank String authorization) {
        loginService.deslogar(authorization);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.LoginRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.LoginResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutenticacaoControllerTest {
    @Mock
    LoginService loginService;

    @InjectMocks
    private AutenticacaoController autenticacaoController;

    @Test
    void deveLogar() {
        LoginResponseDTO loginResponseDTOMock = LoginResponseDTO.builder().token("token").build();
        ResponseEntity<LoginResponseDTO> expected = ResponseEntity.ok(loginResponseDTOMock);

        when(loginService.logar(any())).thenReturn(loginResponseDTOMock);

        ResponseEntity<LoginResponseDTO> result = autenticacaoController.login(new LoginRequestDTO("email", "senha"));
        assertEquals(expected, result);
    }

    @Test
    void deveDeslogar() {
        ResponseEntity<Void> expected = ResponseEntity.status(HttpStatus.OK).build();

        doNothing().when(loginService).deslogar(any());

        ResponseEntity<Void> result = autenticacaoController.logoff("authorization");
        assertEquals(expected, result);
    }
}

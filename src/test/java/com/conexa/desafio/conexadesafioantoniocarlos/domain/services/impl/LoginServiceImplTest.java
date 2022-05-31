package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.LoginRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.LoginResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Login;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.enums.StatusLogin;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.NaoEncontradoException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.LoginRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.JwtService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {
    @Mock
    MedicoService medicoService;
    @Mock
    JwtService jwtService;
    @Mock
    LoginRepository loginRepository;
    @InjectMocks
    LoginServiceImpl loginServiceImpl;

    @Test
    void deveLogar() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Optional<Medico> medicoMock = Optional.of(Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .senha(encoder.encode("senha"))
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build()
        );

        Login loginMock = Login.builder()
                .token("token")
                .medico(medicoMock.get())
                .status(StatusLogin.ATIVO)
                .build();

        LoginResponseDTO loginResponseDTOMock = LoginResponseDTO.builder().token("token").build();

        when(medicoService.recuperarPorEmail(anyString())).thenReturn(medicoMock);
        when(jwtService.generateToken()).thenReturn("generateTokenResponse");
        when(loginRepository.save(any())).thenReturn(loginMock);

        LoginResponseDTO result = loginServiceImpl.logar(new LoginRequestDTO("email", "senha"));

        assertEquals(loginResponseDTOMock, result);
    }

    @Test
    void deveLancarNaoEncontradoExceptionAoLogar() {
        LoginRequestDTO loginRequestDTOMock = new LoginRequestDTO("email", "senha");

        when(medicoService.recuperarPorEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> loginServiceImpl.logar(loginRequestDTOMock));
    }

    @Test
    void testDeslogar() {
        Medico medico = Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .senha("senha")
                .confirmacaoSenha("senha")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();

        Login loginMock = Login.builder()
                .token("token")
                .medico(medico)
                .status(StatusLogin.ATIVO)
                .build();

        when(loginRepository.findByToken(anyString())).thenReturn(loginMock);

        loginServiceImpl.deslogar("token");

        verify(loginRepository, times(1)).save(loginMock);
    }

    @Test
    void deveValidarSenha() {
        boolean result = loginServiceImpl.validarSenha("senhaDigitada", "senha");
        assertFalse(result);
    }

    @Test
    void deveRecuperarPorToken() {
        Medico medico = Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .senha("senha")
                .confirmacaoSenha("senha")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();

        Login loginMock = Login.builder()
                .token("token")
                .medico(medico)
                .status(StatusLogin.ATIVO)
                .build();

        when(loginRepository.findByToken(anyString())).thenReturn(loginMock);

        Login result = loginServiceImpl.recuperarPorToken("token");

        assertEquals(loginMock, result);
    }
}

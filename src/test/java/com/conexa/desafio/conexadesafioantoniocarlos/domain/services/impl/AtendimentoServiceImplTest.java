package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.AtendimentoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.PacienteRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.AtendimentoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Atendimento;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.AtendimentoRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.LoginService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtendimentoServiceImplTest {
    @Mock
    PacienteService pacienteService;
    @Mock
    MedicoService medicoService;
    @Mock
    LoginService loginService;
    @Mock
    AtendimentoRepository atendimentoRepository;
    @InjectMocks
    AtendimentoServiceImpl atendimentoServiceImpl;

    @Test
    void deveAtender() {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(1);
        Paciente pacienteMock = Paciente.builder().id(1).nome("nome").cpf("cpf").build();
        
        Medico medicoMock = Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();
        
        AtendimentoResponseDTO atendimentoResponseDTOMock = AtendimentoResponseDTO.builder()
                .dataHora(localDateTime)
                .medico("nome")
                .paciente("nome")
                .build();

        Atendimento atendimentoMock = Atendimento.builder()
                .dataHora(LocalDateTime.of(2022, Month.MAY, 9, 14, 25, 29))
                .medico(medicoMock)
                .paciente(pacienteMock)
                .build();

        PacienteRequestDTO pacienteRequestDTOMock = new PacienteRequestDTO("nome", "cpf");
        AtendimentoRequestDTO atendimentoRequestDTOMock = new AtendimentoRequestDTO(localDateTime, pacienteRequestDTOMock);

        when(pacienteService.cadastrar(any())).thenReturn(pacienteMock);
        when(medicoService.recuperarPorToken(anyString())).thenReturn(medicoMock);
        when(loginService.validarToken(anyString())).thenReturn(true);
        when(atendimentoRepository.save(any())).thenReturn(atendimentoMock);

        AtendimentoResponseDTO result = atendimentoServiceImpl.atender(atendimentoRequestDTOMock, "authorization");
        assertEquals(atendimentoResponseDTOMock, result);
    }
}

package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.AtendimentoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.PacienteRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.AtendimentoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.AtendimentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtendimentoControllerTest {
    @Mock
    AtendimentoService atendimentoService;
    @InjectMocks
    AtendimentoController atendimentoController;

    @Test
    void deveAtender() {
        AtendimentoResponseDTO atendimentoResponseDTOMock = AtendimentoResponseDTO.builder()
                .dataHora(LocalDateTime.of(2022, Month.MAY, 9, 14, 25, 29))
                .medico("teste")
                .paciente("teste")
                .build();

        AtendimentoRequestDTO atendimentoRequestDTO = new AtendimentoRequestDTO(
                LocalDateTime.of(2022, Month.MAY, 9, 14, 25, 29),
                new PacienteRequestDTO("nome", "604.363.073-75"));

        ResponseEntity<AtendimentoResponseDTO> expected = ResponseEntity.ok(atendimentoResponseDTOMock);

        when(atendimentoService.atender(any(), anyString())).thenReturn(atendimentoResponseDTOMock);

        ResponseEntity<AtendimentoResponseDTO> result = atendimentoController.atender(atendimentoRequestDTO, "authorization");

        assertEquals(expected, result);
    }
}
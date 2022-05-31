package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.MedicoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.MedicoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicoControllerTest {
    @Mock
    MedicoService medicoService;
    @InjectMocks
    MedicoController medicoController;

    @Test
    void deveInscreverSeUmMedico() {
        MedicoResponseDTO medicoResponseDTOMock = MedicoResponseDTO.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();

        ResponseEntity<MedicoResponseDTO> expected = ResponseEntity.ok(medicoResponseDTOMock);

        when(medicoService.inscreverSe(any())).thenReturn(medicoResponseDTOMock);

        ResponseEntity<MedicoResponseDTO> result = medicoController.inscreverSe(new MedicoRequestDTO(
                "nome",
                "cpf",
                "email",
                "senha",
                "senha",
                "especialidade", LocalDate.of(2022, Month.MAY, 9),
                "telefone")
        );

        assertEquals(expected, result);
    }
}

package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.PacienteRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceImplTest {
    @Mock
    PacienteRepository pacienteRepository;
    @InjectMocks
    PacienteServiceImpl pacienteServiceImpl;

    @Test
    void deveCadastrarUmPaciente() {
        Paciente paciente = Paciente.builder().id(1).nome("nome").cpf("cpf").build();

        when(pacienteRepository.save(any())).thenReturn(paciente);

        Paciente result = pacienteServiceImpl.cadastrar(new PacienteRequestDTO("nome", "cpf"));

        assertEquals(paciente, result);
    }
}

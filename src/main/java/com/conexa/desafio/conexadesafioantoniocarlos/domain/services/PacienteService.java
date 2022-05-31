package com.conexa.desafio.conexadesafioantoniocarlos.domain.services;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.PacienteRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;

public interface PacienteService {
    Paciente cadastrar(PacienteRequestDTO pacienteDto);
}

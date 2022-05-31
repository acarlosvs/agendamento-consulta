package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.PacienteRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.PacienteRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    public Paciente cadastrar(PacienteRequestDTO pacienteDto) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByCpf(pacienteDto.getCpf());
        if (pacienteOptional.isPresent())
            return pacienteOptional.get();

        Paciente paciente = Paciente.builder()
                .nome(pacienteDto.getNome())
                .cpf(pacienteDto.getCpf())
                .build();

         return pacienteRepository.save(paciente);
    }
}

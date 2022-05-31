package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.AtendimentoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.AtendimentoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Atendimento;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.HorarioAtendimentoExpiradoException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.AtendimentoRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.AtendimentoService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.LoginService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    private final PacienteService pacienteService;

    private final MedicoService medicoService;

    private final LoginService loginService;

    private final AtendimentoRepository atendimentoRepository;

    @Override
    @Transactional
    public AtendimentoResponseDTO atender(AtendimentoRequestDTO atendimentoDto, String authorization) {
        loginService.validarToken(authorization);

        if (atendimentoDto.getDataHora().isBefore(LocalDateTime.now()))
            throw new HorarioAtendimentoExpiradoException();

        Paciente paciente = pacienteService.cadastrar(atendimentoDto.getPaciente());

        Medico medico = medicoService.recuperarPorToken(authorization);

        Atendimento atendimento = Atendimento.builder()
                .dataHora(atendimentoDto.getDataHora())
                .paciente(paciente)
                .medico(medico)
                .build();

        atendimentoRepository.save(atendimento);

        return AtendimentoResponseDTO.builder()
                .dataHora(atendimento.getDataHora())
                .paciente(paciente.getNome())
                .medico(medico.getNome())
                .build();

    }
}

package com.conexa.desafio.conexadesafioantoniocarlos.domain.services;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.MedicoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.MedicoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;

import java.util.Optional;

public interface MedicoService {
    MedicoResponseDTO inscreverSe(MedicoRequestDTO medicoDto);

    Medico recuperarPorToken(String token);

    Optional<Medico> recuperarPorEmail(String email);
}

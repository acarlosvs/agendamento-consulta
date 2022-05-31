package com.conexa.desafio.conexadesafioantoniocarlos.domain.services;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.AtendimentoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.AtendimentoResponseDTO;

public interface AtendimentoService {
    AtendimentoResponseDTO atender(AtendimentoRequestDTO atendimentoDto, String authorization);
}

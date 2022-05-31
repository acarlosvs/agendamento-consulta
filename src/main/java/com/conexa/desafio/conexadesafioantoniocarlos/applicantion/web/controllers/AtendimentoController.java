package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.AtendimentoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.AtendimentoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import static com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.consts.RoutePaths.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_BASE_PATH + API_VERSION_PATH)
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @PostMapping(ATTENDANCE_PATH)
    public ResponseEntity<AtendimentoResponseDTO> atender(
            @RequestBody @Valid AtendimentoRequestDTO atendimentoDto,
            @RequestHeader @NotBlank String authorization
    ) {
        return ResponseEntity.ok(atendimentoService.atender(atendimentoDto, authorization));
    }
}

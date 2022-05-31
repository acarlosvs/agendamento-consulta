package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.controllers;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.MedicoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.MedicoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.consts.RoutePaths.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(API_BASE_PATH + API_VERSION_PATH)
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping(SINGNUP_PATH)
    public ResponseEntity<MedicoResponseDTO> inscreverSe(@RequestBody @Valid MedicoRequestDTO medicoDto) {
        return ResponseEntity.ok(medicoService.inscreverSe(medicoDto));
    }
}

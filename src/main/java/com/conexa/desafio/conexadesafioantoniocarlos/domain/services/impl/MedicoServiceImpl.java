package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.MedicoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.MedicoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.MedicoExisteException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.SenhaDivergenteException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.MedicoRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.JwtService;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    private final JwtService jwtService;

    @Override
    public MedicoResponseDTO inscreverSe(MedicoRequestDTO medicoDto) {
        Medico medico = Medico.builder()
                .nome(medicoDto.getNome())
                .cpf(medicoDto.getCpf())
                .email(medicoDto.getEmail())
                .senha(jwtService.getPasswordEncoder(medicoDto.getSenha()))
                .confirmacaoSenha(jwtService.getPasswordEncoder(medicoDto.getConfirmacaoSenha()))
                .especialidade(medicoDto.getEspecialidade())
                .dataNascimento(medicoDto.getDataNascimento())
                .telefone(medicoDto.getTelefone())
                .build();

        this.validarMedico(medicoDto);

        medicoRepository.save(medico);

        return MedicoResponseDTO.builder()
                .nome(medico.getNome())
                .cpf(medico.getCpf())
                .email(medico.getEmail())
                .especialidade(medico.getEspecialidade())
                .dataNascimento(medico.getDataNascimento())
                .telefone(medico.getTelefone())
                .build();
    }

    protected void validarMedico(MedicoRequestDTO medicoDto) {
        if (medicoRepository.findByEmail(medicoDto.getEmail()).isPresent())
            throw new MedicoExisteException();

        if (!medicoDto.getSenha().equals(medicoDto.getConfirmacaoSenha()))
            throw new SenhaDivergenteException();
    }

    @Override
    public Medico recuperarPorToken(String token) {
        return medicoRepository.findByToken(token);
    }

    @Override
    public Optional<Medico> recuperarPorEmail(String email) {
        return medicoRepository.findByEmail(email);
    }
}

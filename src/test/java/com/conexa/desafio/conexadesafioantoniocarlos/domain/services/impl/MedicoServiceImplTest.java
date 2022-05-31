package com.conexa.desafio.conexadesafioantoniocarlos.domain.services.impl;

import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.request.MedicoRequestDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.commons.dtos.response.MedicoResponseDTO;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.MedicoExisteException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.SenhaDivergenteException;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories.MedicoRepository;
import com.conexa.desafio.conexadesafioantoniocarlos.domain.services.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicoServiceImplTest {
    @Mock
    MedicoRepository medicoRepository;
    @Mock
    JwtService jwtService;
    @InjectMocks
    MedicoServiceImpl medicoServiceImpl;

    @Test
    void deveInscreverUmMedico() {
        MedicoRequestDTO medicoRequestDTOMock = new MedicoRequestDTO(
                "nome",
                "cpf",
                "email",
                "senha",
                "senha",
                "especialidade", LocalDate.of(2022, Month.MAY, 9),
                "telefone"
        );

        MedicoResponseDTO medicoResponseDTOMock = MedicoResponseDTO.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();

        when(medicoRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(jwtService.getPasswordEncoder(anyString())).thenReturn("getPasswordEncoderResponse");

        MedicoResponseDTO result = medicoServiceImpl.inscreverSe(medicoRequestDTOMock);

        assertEquals(medicoResponseDTOMock, result);
    }

    @Test
    void deveValidarMedicoSemLancarException() {
        MedicoRequestDTO medicoRequestDTOMock = new MedicoRequestDTO(
                "nome",
                "cpf",
                "email",
                "senha",
                "senha",
                "especialidade", LocalDate.of(2022, Month.MAY, 9),
                "telefone"
        );

        when(medicoRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        medicoServiceImpl.validarMedico(medicoRequestDTOMock);

        verify(medicoRepository, times(1)).findByEmail(medicoRequestDTOMock.getEmail());
    }

    @Test()
    void quandoMedicoJaExisteDeveLancarMedicoExisteException() {
        Optional<Medico> medicoMock = Optional.of(Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build()
        );

        MedicoRequestDTO medicoRequestDTOMock = new MedicoRequestDTO(
                "nome",
                "cpf",
                "email",
                "senha",
                "senha",
                "especialidade", LocalDate.of(2022, Month.MAY, 9),
                "telefone"
        );

        when(medicoRepository.findByEmail(anyString())).thenReturn(medicoMock);

        assertThrows(MedicoExisteException.class, () -> medicoServiceImpl.validarMedico(medicoRequestDTOMock));
    }

    @Test()
    void quandoSenhasForemDiferentesDeveLancarSenhaDivergenteException() {
        MedicoRequestDTO medicoRequestDTOMock = new MedicoRequestDTO(
                "nome",
                "cpf",
                "email",
                "senha",
                "confirmacaoSenha",
                "especialidade", LocalDate.of(2022, Month.MAY, 9),
                "telefone"
        );

        when(medicoRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(SenhaDivergenteException.class, () -> medicoServiceImpl.validarMedico(medicoRequestDTOMock));
    }

    @Test
    void deveRecuperarUmMedicoPorToken() {
        Medico medico = Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build();

        when(medicoRepository.findByToken(anyString())).thenReturn(medico);

        Medico result = medicoServiceImpl.recuperarPorToken("token");
        assertEquals(medico, result);
    }

    @Test
    void deveRecuperarUmMedicoPorEmail() {
        Optional<Medico> medico = Optional.of(Medico.builder()
                .nome("nome")
                .cpf("cpf")
                .email("email")
                .senha("senha")
                .confirmacaoSenha("senha")
                .especialidade("especialidade")
                .dataNascimento(LocalDate.of(2022, Month.MAY, 9))
                .telefone("telefone")
                .build()
        );

        when(medicoRepository.findByEmail(anyString())).thenReturn(medico);

        Optional<Medico> result = medicoServiceImpl.recuperarPorEmail("email");
        assertEquals(medico, result);
    }
}

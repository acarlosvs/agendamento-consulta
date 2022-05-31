package com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, BigInteger> {
    Optional<Paciente> findByCpf(@Param("cpf") String cpf);
}

package com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, BigInteger> {
}

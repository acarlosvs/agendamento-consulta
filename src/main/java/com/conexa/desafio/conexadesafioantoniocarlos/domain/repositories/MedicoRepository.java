package com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, BigInteger> {
    @Query("select m from Login l join l.medico m where l.token = :token")
    Medico findByToken(@Param("token") String token);

    Optional<Medico> findByEmail(@Param("email") String email);
}

package com.conexa.desafio.conexadesafioantoniocarlos.domain.repositories;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface LoginRepository extends JpaRepository<Login, BigInteger> {
    Login findByToken(@Param("token") String token);
}

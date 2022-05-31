package com.conexa.desafio.conexadesafioantoniocarlos.domain.entities;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.enums.StatusLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "status", nullable = false)
    private StatusLogin status;
}
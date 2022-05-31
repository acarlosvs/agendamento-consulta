package com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions;

public class HorarioAtendimentoExpiradoException extends RuntimeException {
    public HorarioAtendimentoExpiradoException() {
        super("Você deve criar um agendamento com uma data futura");
    }

}

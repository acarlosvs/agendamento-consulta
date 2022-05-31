package com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions;

public class MedicoExisteException extends RuntimeException {
    public MedicoExisteException() {
        super("Medico já cadastrado.");
    }

}

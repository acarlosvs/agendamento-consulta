package com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions;

public class SenhaDivergenteException extends RuntimeException {
    public SenhaDivergenteException() {
        super("As senhas informadas são divergentes");
    }

}

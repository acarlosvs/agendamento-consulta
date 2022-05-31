package com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions;

public class TokenExpiradoException extends RuntimeException {
    public TokenExpiradoException() {
        super("Token expirado.");
    }

}

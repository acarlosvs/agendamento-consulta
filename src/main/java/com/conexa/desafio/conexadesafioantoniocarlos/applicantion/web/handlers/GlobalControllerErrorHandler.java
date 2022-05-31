package com.conexa.desafio.conexadesafioantoniocarlos.applicantion.web.handlers;

import com.conexa.desafio.conexadesafioantoniocarlos.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMethodNotValidException(MethodArgumentNotValidException ex ) {
        List<String> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " " + error.getDefaultMessage());
        }

        return new ApiErrors(errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrors handleConstraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        List<String> errors = new ArrayList<>(constraintViolations.size());
        errors.addAll(constraintViolations.stream()
                .map(constraintViolation -> String.format("%s '%s' %s", constraintViolation.getPropertyPath(),
                        constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                .collect(Collectors.toList()));

        return new ApiErrors(errors);
    }

    @ExceptionHandler(HorarioAtendimentoExpiradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleHorarioAtendimentoExpiradoException(HorarioAtendimentoExpiradoException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handleNaoEncontradoException( NaoEncontradoException ex ) {
        return new ApiErrors(ex.getMessage());
    }

    @ExceptionHandler(SenhaDivergenteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleSenhaDivergenteException(SenhaDivergenteException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(TokenExpiradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleTokenExpiradoException(TokenExpiradoException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(TokenInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleTokenInvalidoException(TokenInvalidoException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(MedicoExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleMedicoExisteException(MedicoExisteException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
}

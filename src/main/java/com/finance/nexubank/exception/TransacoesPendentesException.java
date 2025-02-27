package com.finance.nexubank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TransacoesPendentesException extends RuntimeException {
    public TransacoesPendentesException(String mensagem) {
        super(mensagem);
    }
}
package br.com.fiap.finalchallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class InvalidTransactionExceptionHandler extends RuntimeException{
	
	public InvalidTransactionExceptionHandler(String message) {
        super(message);
    }
}

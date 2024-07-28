package br.com.barbearia_api;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}

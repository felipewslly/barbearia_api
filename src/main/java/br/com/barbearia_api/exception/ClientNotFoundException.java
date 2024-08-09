package br.com.barbearia_api.exception;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException (String message){
        super(message);
    }
}

package com.estudo.cantabank.cantabank.exception;

public class ContaNaoEncontradaException extends RuntimeException {
    public ContaNaoEncontradaException(String messge){
        super(messge);
    }
}

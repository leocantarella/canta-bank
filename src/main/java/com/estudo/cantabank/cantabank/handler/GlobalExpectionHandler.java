package com.estudo.cantabank.cantabank.handler;

import com.estudo.cantabank.cantabank.exception.NaoEncontadoExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExpectionHandler {

    //Conta não encontrada
    @ExceptionHandler(NaoEncontadoExeption.class)
    public ResponseEntity<Map<String, Object>> handleContaNaoEncontrada(NaoEncontadoExeption ex){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro", ex.getMessage());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        body.put("Timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    //Exception Global
    public ResponseEntity<Map<String, Object>> handleGeral(Exception ex){
        Map<String, Object> body = new HashMap<>();
        body.put("Erro", ex.getMessage());
        body.put("Status", HttpStatus.NOT_FOUND.value());
        body.put("Timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}

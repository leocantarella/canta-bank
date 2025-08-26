package com.estudo.cantabank.cantabank.controller;

import com.estudo.cantabank.cantabank.dto.DepositoRequest;
import com.estudo.cantabank.cantabank.dto.SaqueRequest;
import com.estudo.cantabank.cantabank.dto.TransferenciaRequest;
import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.service.ContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    //Método de criação de contas
    @PostMapping("/criarconta")
    public ResponseEntity<Conta> criarConta(@RequestBody  Conta conta){
        Conta novaConta = contaService.criarConta(conta);
        return ResponseEntity.status(201).body(novaConta);
    }

    //Método para transferência
    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestBody @Valid TransferenciaRequest request){
        contaService.transferir(
                request.getIdCliente(),
                request.getIdContaSaida(),
                request.getIdContaDestino(),
                request.getValor()
        );
        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }

    //Método para saque
    @PostMapping("/saque")
    public ResponseEntity<String> sacar(@RequestBody @Valid SaqueRequest saqueRequest){
        contaService.sacar(
                saqueRequest.getIdConta(),
                saqueRequest.getValor(),
                saqueRequest.getIdCliente()
        );
        return ResponseEntity.ok("Saque realizado com sucesso!");
    }

    //Método para depósito
    @PostMapping("/deposito")
    public ResponseEntity<String> depositar(@RequestBody @Valid DepositoRequest depositoRequest){
        contaService.depositar(depositoRequest);
        return ResponseEntity.ok("Depósito realizado com sucesso!");
    }
}

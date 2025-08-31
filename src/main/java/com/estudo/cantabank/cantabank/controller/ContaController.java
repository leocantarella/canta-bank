package com.estudo.cantabank.cantabank.controller;

import ch.qos.logback.core.net.server.Client;
import com.estudo.cantabank.cantabank.dto.*;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.service.ContaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;

    //Método de criação de contas
    @PostMapping("/criar/{clienteId}")
    public ResponseEntity<Long> criarConta(
            @PathVariable Long clienteId,
            @RequestBody @Valid CriarContaRequest request) {

        // Cria a conta
        Conta contaCriada = contaService.criarConta(clienteId, request);

        // Retorna 201 Created e o ID da nova conta
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contaCriada.getId());
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

    //Listar contas
    @GetMapping("/lista")
    public List<Conta> listarContas() {
       return contaService.listarContas();
    }
}

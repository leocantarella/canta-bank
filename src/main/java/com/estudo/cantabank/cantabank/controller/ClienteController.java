package com.estudo.cantabank.cantabank.controller;

import com.estudo.cantabank.cantabank.dto.CriarClienteRequest;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/criar")
    public ResponseEntity<String> criarCliente(@RequestBody @Valid CriarClienteRequest request){
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setIdade(request.getIdade());
        clienteService.cadastroCliente(cliente);
        return ResponseEntity.ok("Cliente cadastrado com sucesso!");
    }


}

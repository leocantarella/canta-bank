package com.estudo.cantabank.cantabank.controller;

import com.estudo.cantabank.cantabank.dto.CriarClienteRequest;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/criar")
    public ResponseEntity<String> criarCliente(@RequestBody @Valid CriarClienteRequest request){
        clienteService.cadastroCliente(request);
        return ResponseEntity.ok("Cliente cadastrado com sucesso!");
    }

    @GetMapping("/listar")
    public List<Cliente> listarClientes(){
       return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public Cliente localizarId(@PathVariable Long id){
        return clienteService.encontrarClienteId(id);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String>deletarCliente(@PathVariable Long id){
        clienteService.excluirCliente(id);
        return ResponseEntity.ok("Cliente excluido com sucesso!");
    }


}

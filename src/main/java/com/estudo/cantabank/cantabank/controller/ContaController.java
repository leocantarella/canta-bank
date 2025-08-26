package com.estudo.cantabank.cantabank.controller;

import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor

@RestController
@RequestMapping("/conta")
public class ContaControlller {

    private final ContaService contaService;

    @PostMapping("/criarconta")
    public void criarConta(Conta conta){
        contaService.criarConta(conta);
    }


}

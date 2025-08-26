package com.estudo.cantabank.cantabank.service;


import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    //Método de criação de conta

    public Conta criarConta(Conta conta){
      return  contaRepository.save(conta);
    }

    //Método de depoósito
    public void depositar(Long id, Double valor){
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        if (valor <= 0) {
            throw new RuntimeException("Valor inválido!");
        }
        conta.setSaldo(conta.getSaldo()+ valor);
        contaRepository.save(conta);
    }

    //Método para saque
    public void sacar(Long idConta, Double valor, Long idCliente){
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        if (!conta.getCliente().getId().equals(idCliente)){
            throw new RuntimeException("O usuário não é o dono da conta!");}

        if (conta.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente!");
        }

        if (valor <= 0){
            throw new RuntimeException("Valor inválido!");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);
    }


}

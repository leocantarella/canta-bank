package com.estudo.cantabank.cantabank.service;
import com.estudo.cantabank.cantabank.dto.DepositoRequest;
import com.estudo.cantabank.cantabank.exception.ContaNaoEncontradaException;
import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    //Método de criação de conta

    public Conta criarConta(Conta conta){
      return  contaRepository.save(conta);
    }
    
    //Método de listagem de conta por ID
    public Conta localizarContaId(Long id){
      return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não localizada!"));
    }

    //Método de depoósito
    public void depositar(DepositoRequest request){
        Conta conta = contaRepository.findById(request.getIdConta())
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta com o ID" + request.getIdConta() + " não encontrada!"));
        if (request.getValor() <= 0) {
            throw new RuntimeException("Valor inválido!");
        }
        conta.setSaldo(conta.getSaldo()+ request.getValor());
        contaRepository.save(conta);
    }

    @Transactional
    //Método para saque
    public void sacar(Long idConta, Double valor, Long idCliente){
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada!"));
        if (!conta.getCliente().getId().equals(idCliente)){
            throw new ContaNaoEncontradaException("O usuário não é o dono da conta!");}

        if (conta.getSaldo() < valor) {
            throw new ContaNaoEncontradaException("Saldo insuficiente!");
        }

        if (valor <= 0){
            throw new ContaNaoEncontradaException("Valor inválido!");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);
    }

    // método para transferência
    public void transferir(Long idCliente, Long idContaSaida, Long idContaDestino, Double valor){
        Conta contaSaida = contaRepository.findById(idContaSaida).
                orElseThrow(() -> new ContaNaoEncontradaException("Conta saída não encontrada"));
        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta destino não encontrada"));
        if (!contaSaida.getCliente().getId().equals(idCliente)){
            throw new ContaNaoEncontradaException("O cliente não é dono da conta!");
        }
        if (contaSaida.getSaldo() < valor){
            throw new RuntimeException("Saldo insuficiente!");
        }
        if (valor <= 0){
            throw new RuntimeException("Valor inválido!");
        }
        contaSaida.setSaldo(contaSaida.getSaldo() - valor);
        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        contaRepository.save(contaSaida);
        contaRepository.save(contaDestino);
    }

}

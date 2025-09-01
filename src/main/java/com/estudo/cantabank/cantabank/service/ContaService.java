package com.estudo.cantabank.cantabank.service;
import com.estudo.cantabank.cantabank.dto.CriarContaRequest;
import com.estudo.cantabank.cantabank.dto.DepositoRequest;
import com.estudo.cantabank.cantabank.exception.NaoEncontadoExeption;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.model.Conta;
import com.estudo.cantabank.cantabank.repository.ClienteRepository;
import com.estudo.cantabank.cantabank.repository.ContaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteRepository clienteRepository;

    //Método de criação de conta

    public Conta criarConta(Long id, CriarContaRequest request) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow
                (() -> new RuntimeException("Cliente não encontrado!"));
        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setSaldo(request.getSaldo());
        return contaRepository.save(conta);
    }

    //Listar contas
    public List<Conta> listarContas(){
        return contaRepository.findAll();
    }
    
    //Método de listagem de conta por ID
    public Conta localizarContaId(Long id){
      return contaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não localizada!"));
    }

    //Método de depoósito
    public void depositar(DepositoRequest request){
        Conta conta = contaRepository.findById(request.getIdConta())
                .orElseThrow(() -> new NaoEncontadoExeption("Conta com o ID" + request.getIdConta() + " não encontrada!"));
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
                .orElseThrow(() -> new NaoEncontadoExeption("Conta não encontrada!"));
        if (!conta.getCliente().getId().equals(idCliente)){
            throw new NaoEncontadoExeption("O usuário não é o dono da conta!");}

        if (conta.getSaldo() < valor) {
            throw new NaoEncontadoExeption("Saldo insuficiente!");
        }

        if (valor <= 0){
            throw new NaoEncontadoExeption("Valor inválido!");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);
    }

    // método para transferência
    public void transferir(Long idCliente, Long idContaSaida, Long idContaDestino, Double valor){
        Conta contaSaida = contaRepository.findById(idContaSaida).
                orElseThrow(() -> new NaoEncontadoExeption("Conta saída não encontrada"));
        Conta contaDestino = contaRepository.findById(idContaDestino)
                .orElseThrow(() -> new NaoEncontadoExeption("Conta destino não encontrada"));
        if (!contaSaida.getCliente().getId().equals(idCliente)){
            throw new NaoEncontadoExeption("O cliente não é dono da conta!");
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

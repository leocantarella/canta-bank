package com.estudo.cantabank.cantabank.service;

import com.estudo.cantabank.cantabank.exception.ContaNaoEncontradaException;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    //Cadastrar cliente
    public Cliente cadastroCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    // Listar Clientes
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    //Encontrar cliente por ID
    public Cliente encontrarClienteId(Long id){
        return clienteRepository.findById(id).
                orElseThrow(() -> new ContaNaoEncontradaException("Cliente não encontrado!"));
    }

    //Atualizar cliente existente
    public Cliente editarCliente(Long id, Cliente att){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Cliente não encontrado"));
        cliente.setNome(att.getNome());
        cliente.setIdade(att.getIdade());
        cliente.setCpf(att.getCpf());
        return clienteRepository.save(cliente);
    }

    //Remover cliente
    public void excluirCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException("Cliente não encontrado"));
         clienteRepository.deleteById(id);
    }

}

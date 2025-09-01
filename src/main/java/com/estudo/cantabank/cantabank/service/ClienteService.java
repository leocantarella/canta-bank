package com.estudo.cantabank.cantabank.service;

import com.estudo.cantabank.cantabank.dto.CriarClienteRequest;
import com.estudo.cantabank.cantabank.exception.NaoEncontadoExeption;
import com.estudo.cantabank.cantabank.model.Cliente;
import com.estudo.cantabank.cantabank.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    //Cadastrar cliente
    public Cliente cadastroCliente(CriarClienteRequest request){
        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setCpf(request.getCpf());
        cliente.setIdade(request.getIdade());
        return clienteRepository.save(cliente);
    }

    // Listar Clientes
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    //Encontrar cliente por ID
    public Cliente encontrarClienteId(Long id){
        return clienteRepository.findById(id).
                orElseThrow(() -> new NaoEncontadoExeption("Cliente não encontrado!"));
    }

    //Atualizar cliente existente
    public Cliente editarCliente(Long id, CriarClienteRequest request){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontadoExeption("Cliente não encontrado"));
        cliente.setNome(request.getNome());
        cliente.setIdade(request.getIdade());
        cliente.setCpf(request.getCpf());
        return clienteRepository.save(cliente);
    }

    //Remover cliente
    public void excluirCliente(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontadoExeption("Cliente não encontrado"));
         clienteRepository.deleteById(id);
    }

}

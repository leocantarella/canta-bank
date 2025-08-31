package com.estudo.cantabank.cantabank.dto;

import com.estudo.cantabank.cantabank.model.Conta;
import jakarta.validation.constraints.NotNull;

public class ContaResponseDTO {
    private Long id;
    @NotNull(message = "É necessario informar o ID do cliente")
    private Long idCliente;
    private Double saldo;

    public ContaResponseDTO(Conta conta){
        this.id = conta.getId();
        this.idCliente = conta.getCliente().getId();
        this.saldo = conta.getSaldo();

    }
}

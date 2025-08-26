package com.estudo.cantabank.cantabank.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransferenciaRequest {

    @NotNull(message = "O ID cliente não pode ser nulo!")
    private Long idCliente;

    @NotNull(message = "O ID da conta saída não pode ser nulo")
    private Long idContaSaida;

    @NotNull(message = "O ID da conta destino não pode ser nulo")
    private Long idContaDestino;

    @NotNull(message = "O valor não pode ser nulo")
    @DecimalMin(value = "0.01", message = "O valor precisa ser maior que 0!")
    private Double valor;

}


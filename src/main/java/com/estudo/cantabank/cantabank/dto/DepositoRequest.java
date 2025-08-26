package com.estudo.cantabank.cantabank.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepositoRequest {

    @NotNull(message = "O ID da conta é obrigatório!")
    private Long idConta;

    @NotNull(message = "O valor é obrigatório!")
    @Min(value = 1, message = "O valor mínimo para depósito é de R$10,00")
    private Double valor;

}

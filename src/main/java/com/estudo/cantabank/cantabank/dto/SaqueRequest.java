package com.estudo.cantabank.cantabank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SaqueRequest {

    @NotNull(message = "O ID da conta é obrigatório!")
    private Long idConta;

    @NotNull(message = "O ID do cliente é obrigatório!")
    private Long idCliente;

    @NotNull(message = "O valor é obrigatório!")
    @Min(value = 10, message = "O valor mínimo de saque é R$10,00")
    private Double valor;






}

package com.estudo.cantabank.cantabank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarContaRequest {

    @Min(value = 0, message = "O saldo inicial não pode ser negativo")
    private double saldo;
}

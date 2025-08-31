package com.estudo.cantabank.cantabank.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CriarClienteRequest {

    @NotNull(message = "O nome não pode ser nulo!")
    private String nome;

    @NotNull(message = "O CPF não pode ser nulo!")
    private String cpf;

    @NotNull(message = "A idade não pode ser nula!")
    @Min(value = 18, message = "A idade mínima para criação de conta é de 18 anos!")
    private int idade;


}

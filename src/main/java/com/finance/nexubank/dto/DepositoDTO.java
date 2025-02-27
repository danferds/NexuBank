package com.finance.nexubank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DepositoDTO {
    @NotBlank(message = "Número da conta é obrigatório")
    private String numeroConta;

    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "^[0-9]{4}$", message = "Agência deve conter 4 dígitos")
    private String agencia;

    @Positive(message = "Valor deve ser positivo")
    private double valor;
}
package com.finance.nexubank.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class UsuarioAtualizacaoDTO {

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Telefone não pode ser vazio")
    @Pattern(
            regexp = "^(\\(\\d{2}\\)\\s?\\d{4,5}-?\\d{4})$",
            message = "Telefone inválido. Use (XX)XXXX-XXXX ou (XX)XXXXX-XXXX."
    )
    private String telefone;

    @PositiveOrZero(message = "Renda mensal não pode ser negativa")
    private double rendaMensal;
}
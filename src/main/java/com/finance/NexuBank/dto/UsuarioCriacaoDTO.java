package com.finance.nexubank.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioCriacaoDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Documento não pode ser vazio")
    @Pattern(
            regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$",
            message = "Documento inválido. Use CPF (XXX.XXX.XXX-XX) ou CNPJ (XX.XXX.XXX/XXXX-XX)."
    )
    private String documento;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "A senha deve incluir pelo menos uma letra maiúscula, uma minúscula, um número e um dos caracteres especiais: @$!%*?&"
    )
    private String senha;

    @NotBlank(message = "Telefone não pode ser vazio")
    @Pattern(
            regexp = "^(\\(\\d{2}\\)\\s?\\d{4,5}-?\\d{4})$",
            message = "Telefone inválido. Use (XX)XXXX-XXXX ou (XX)XXXXX-XXXX."
    )
    private String telefone;

    @PositiveOrZero(message = "Renda mensal não pode ser negativa")
    private double rendaMensal;
}
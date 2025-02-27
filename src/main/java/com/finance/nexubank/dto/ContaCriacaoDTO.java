package com.finance.nexubank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContaCriacaoDTO {
    @NotBlank(message = "Agência é obrigatória")
    @Pattern(regexp = "^[0-9]{4}$", message = "Agência deve conter 4 dígitos")
    private String agencia;

    @NotBlank(message = "Documento do usuário é obrigatório")
    private String documentoUsuario;

    private String tipoConta;

    private Double limiteChequeEspecial;
    private Double limiteTransacaoDiaria;
    private Double taxaRendimentoMensal;
    private String razaoSocial;
    private Double limiteCredito;
    private Integer limiteUsuariosAutorizados;
}

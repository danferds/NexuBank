package com.finance.nexubank.dto;

import lombok.Data;

@Data
public class DepositoResponseDTO {
    private double valorDepositado;
    private String numeroConta;
    private String agencia;
}

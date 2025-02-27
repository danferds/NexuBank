package com.finance.nexubank.dto;

import lombok.Data;

@Data
public class TransacaoResponseDTO {
    private String tipoTransacao;
    private double valor;
    private String numeroConta;
}

package com.finance.nexubank.dto;

import lombok.Data;

@Data
public class RendimentoPoupancaResponseDTO {
    private double rendimento;
    private double novoSaldo;
    private String numeroConta;
}

package com.finance.nexubank.dto;

import lombok.Data;

@Data
public class ContaResponseDTO {
    private String contaId;
    private String numeroConta;
    private String agencia;
    private double saldo;
    private String status;
    private String tipoConta;
    private String documentoUsuario;
}

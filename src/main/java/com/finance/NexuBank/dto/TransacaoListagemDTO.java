package com.finance.nexubank.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransacaoListagemDTO {
    private String tipoTransacao;
    private String operacao;
    private double valor;
    private LocalDateTime data;
}
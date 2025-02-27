package com.finance.nexubank.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ChavePixResponseDTO {
    private UUID id;
    private String valor;
    private String tipo;
    private LocalDateTime dataCriacao;
}
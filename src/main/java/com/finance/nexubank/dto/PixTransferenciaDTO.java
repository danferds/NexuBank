package com.finance.nexubank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PixTransferenciaDTO extends TransacaoDTO {
    @NotBlank(message = "Chave de destino é obrigatória")
    private String chaveDestino;
}
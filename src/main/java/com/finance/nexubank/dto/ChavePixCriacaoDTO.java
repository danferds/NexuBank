package com.finance.nexubank.dto;

import com.finance.nexubank.model.ChavePixTipo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChavePixCriacaoDTO {
    @NotNull(message = "Tipo da chave Pix é obrigatório")
    private ChavePixTipo tipo;
    private String valor; // CPF, CNPJ, e-mail, telefone ou chave aleatória
}

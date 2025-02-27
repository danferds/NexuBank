package com.finance.nexubank.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.finance.nexubank.model.TipoTransacao;
import lombok.Data;

@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "operacao",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PixTransferenciaDTO.class, name = "TRANSFERENCIA")
})
public class TransacaoDTO {
    private TipoTransacao tipo;
    private String operacao;
    private double valor;
}
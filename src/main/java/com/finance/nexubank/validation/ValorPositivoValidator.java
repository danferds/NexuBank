package com.finance.nexubank.validation;

import com.finance.nexubank.dto.PixTransferenciaDTO;

public class ValorPositivoValidator implements PixTransferValidationHandler {

    private PixTransferValidationHandler next;

    @Override
    public void setNext(PixTransferValidationHandler next) {
        this.next = next;
    }

    @Override
    public void validate(PixTransferenciaDTO dto) throws Exception {
        if (dto.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser positivo.");
        }
        if (next != null) {
            next.validate(dto);
        }
    }
}
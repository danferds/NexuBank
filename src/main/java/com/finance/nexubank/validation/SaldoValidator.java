package com.finance.nexubank.validation;

import com.finance.nexubank.dto.PixTransferenciaDTO;
import com.finance.nexubank.model.AbstractConta;

public class SaldoValidator implements PixTransferValidationHandler {

    private PixTransferValidationHandler next;
    private final AbstractConta contaOrigem;

    public SaldoValidator(AbstractConta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    @Override
    public void setNext(PixTransferValidationHandler next) {
        this.next = next;
    }

    @Override
    public void validate(PixTransferenciaDTO dto) throws Exception {
        if (contaOrigem.getSaldo() < dto.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência.");
        }
        if (next != null) {
            next.validate(dto);
        }
    }
}

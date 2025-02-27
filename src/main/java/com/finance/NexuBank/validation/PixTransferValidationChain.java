package com.finance.nexubank.validation;

import com.finance.nexubank.dto.PixTransferenciaDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.repository.ChavePixRepository;

public class PixTransferValidationChain {

    public static void validate(AbstractConta contaOrigem, PixTransferenciaDTO dto, ChavePixRepository chavePixRepository) throws Exception {
        SaldoValidator saldoValidator = new SaldoValidator(contaOrigem);
        ValorPositivoValidator valorValidator = new ValorPositivoValidator();
        ChaveDestinoValidator chaveDestinoValidator = new ChaveDestinoValidator(chavePixRepository);

        saldoValidator.setNext(valorValidator);
        valorValidator.setNext(chaveDestinoValidator);

        saldoValidator.validate(dto);
    }
}
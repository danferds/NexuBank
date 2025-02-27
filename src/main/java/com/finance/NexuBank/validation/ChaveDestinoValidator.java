package com.finance.nexubank.validation;

import com.finance.nexubank.dto.PixTransferenciaDTO;
import com.finance.nexubank.exception.ContaNotFoundException;
import com.finance.nexubank.model.ChavePix;
import com.finance.nexubank.repository.ChavePixRepository;

import java.util.Optional;

public class ChaveDestinoValidator implements PixTransferValidationHandler {

    private PixTransferValidationHandler next;
    private final ChavePixRepository chavePixRepository;

    public ChaveDestinoValidator(ChavePixRepository chavePixRepository) {
        this.chavePixRepository = chavePixRepository;
    }

    @Override
    public void setNext(PixTransferValidationHandler next) {
        this.next = next;
    }

    @Override
    public void validate(PixTransferenciaDTO dto) throws Exception {
        Optional<ChavePix> chaveDestino = chavePixRepository.findByValor(dto.getChaveDestino());
        if (!chaveDestino.isPresent()) {
            throw new ContaNotFoundException("Chave de destino não encontrada ou não associada a uma conta.");
        }
        if (next != null) {
            next.validate(dto);
        }
    }
}
package com.finance.nexubank.validation;

import com.finance.nexubank.dto.PixTransferenciaDTO;

public interface PixTransferValidationHandler {
    void setNext(PixTransferValidationHandler next);
    void validate(PixTransferenciaDTO dto) throws Exception;
}

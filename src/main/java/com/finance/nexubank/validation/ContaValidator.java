package com.finance.nexubank.validation;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class ContaValidator {

    /**
     * Valida se o documento possui tamanho válido (CPF ou CNPJ).
     */
    public void validarDocumento(ContaCriacaoDTO dto) {
        String documentoLimpo = dto.getDocumentoUsuario().replaceAll("\\D", "");
        if (documentoLimpo.length() != Constants.CPF_LENGTH && documentoLimpo.length() != Constants.CNPJ_LENGTH) {
            throw new IllegalArgumentException("Documento inválido. Informe um CPF ou CNPJ válido.");
        }
    }

    /**
     * Valida o campo tipoConta para usuários com CPF.
     * Exige que seja informado CORRENTE ou POUPANCA.
     */
    public void validarTipoContaParaCPF(ContaCriacaoDTO dto) {
        String documentoLimpo = dto.getDocumentoUsuario().replaceAll("\\D", "");
        if (documentoLimpo.length() == Constants.CPF_LENGTH) {
            if (dto.getTipoConta() == null || dto.getTipoConta().trim().isEmpty()) {
                throw new IllegalArgumentException("Para usuários com CPF, informe o tipo da conta (CORRENTE ou POUPANCA).");
            }
            try {
                com.finance.nexubank.model.TipoConta tipoConta = com.finance.nexubank.model.TipoConta.valueOf(dto.getTipoConta().toUpperCase());
                if (!(tipoConta == com.finance.nexubank.model.TipoConta.CORRENTE || tipoConta == com.finance.nexubank.model.TipoConta.POUPANCA)) {
                    throw new IllegalArgumentException("Para usuários com CPF, os tipos permitidos são: CORRENTE ou POUPANCA.");
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Tipo de conta inválido para CPF. Os permitidos são: CORRENTE ou POUPANCA.");
            }
        }
    }

    /**
     * Valida que, para usuários com CNPJ, se o campo tipoConta for informado, ele deve ser EMPRESARIAL.
     */
    public void validarTipoContaParaCNPJ(ContaCriacaoDTO dto) {
        String documentoLimpo = dto.getDocumentoUsuario().replaceAll("\\D", "");
        if (documentoLimpo.length() == Constants.CNPJ_LENGTH) {
            if (dto.getTipoConta() != null && !dto.getTipoConta().trim().isEmpty() &&
                    !dto.getTipoConta().equalsIgnoreCase("EMPRESARIAL")) {
                throw new IllegalArgumentException("Para usuários com CNPJ, o tipo de conta deve ser EMPRESARIAL.");
            }
        }
    }
}

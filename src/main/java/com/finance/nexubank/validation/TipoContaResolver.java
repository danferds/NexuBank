package com.finance.nexubank.validation;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.model.TipoConta;
import com.finance.nexubank.utils.Constants;
import org.springframework.stereotype.Component;

@Component
public class TipoContaResolver {

    public TipoConta resolverTipoConta(ContaCriacaoDTO dto) {
        String documentoLimpo = dto.getDocumentoUsuario().replaceAll("\\D", "");
        if (documentoLimpo.length() == Constants.CPF_LENGTH) {
            TipoConta tipo = TipoConta.valueOf(dto.getTipoConta().toUpperCase());
            if (tipo != TipoConta.CORRENTE && tipo != TipoConta.POUPANCA) {
                throw new IllegalArgumentException("Para usuários com CPF, os tipos permitidos são CORRENTE ou POUPANCA.");
            }

            return tipo;

        } else if (documentoLimpo.length() == Constants.CNPJ_LENGTH) {
            return TipoConta.EMPRESARIAL;
        } else {
            throw new IllegalArgumentException("Documento inválido. Informe um CPF ou CNPJ válido.");
        }
    }
}

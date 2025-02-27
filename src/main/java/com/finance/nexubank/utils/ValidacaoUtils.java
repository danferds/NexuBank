package com.finance.nexubank.utils;

import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.AbstractConta;

public class ValidacaoUtils {

    public static void validarTransacao(TransacaoDTO transacaoDTO) {
        if (transacaoDTO == null) {
            throw new IllegalArgumentException("Transação não pode ser nula");
        }
        if (transacaoDTO.getValor() <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser maior que zero");
        }
        if (transacaoDTO.getOperacao() == null || transacaoDTO.getOperacao().trim().isEmpty()) {
            throw new IllegalArgumentException("Operação da transação deve ser informada");
        }
    }

    public static void validarConta(AbstractConta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }
    }
}

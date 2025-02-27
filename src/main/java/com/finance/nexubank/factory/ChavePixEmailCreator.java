package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import org.springframework.stereotype.Component;

@Component
public class ChavePixEmailCreator implements ChavePixCreator {
    @Override
    public String criarChave(ChavePixCriacaoDTO dto, AbstractConta conta) {
        if (dto.getValor() == null || dto.getValor().trim().isEmpty()) {
            throw new IllegalArgumentException("Para EMAIL, o valor da chave é obrigatório.");
        }
        return dto.getValor();
    }
}
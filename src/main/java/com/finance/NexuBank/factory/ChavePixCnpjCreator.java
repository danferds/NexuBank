package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import org.springframework.stereotype.Component;

@Component
public class ChavePixCnpjCreator implements ChavePixCreator {
    @Override
    public String criarChave(ChavePixCriacaoDTO dto, AbstractConta conta) {
        return conta.getUsuario().getDocumento();
    }
}
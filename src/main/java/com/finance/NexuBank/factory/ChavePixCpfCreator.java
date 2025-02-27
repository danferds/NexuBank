package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import org.springframework.stereotype.Component;

@Component
public class ChavePixCpfCreator implements ChavePixCreator {
    @Override
    public String criarChave(ChavePixCriacaoDTO dto, AbstractConta conta) {
        return conta.getUsuario().getDocumento();
    }
}
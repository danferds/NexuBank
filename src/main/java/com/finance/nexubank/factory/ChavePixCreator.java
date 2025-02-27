package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;

public interface ChavePixCreator {
    String criarChave(ChavePixCriacaoDTO dto, AbstractConta conta);
}
package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.TipoConta;

public interface ContaCreator {
    AbstractConta criarConta(ContaCriacaoDTO dto);
    TipoConta getTipoConta();
}
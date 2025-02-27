package com.finance.nexubank.strategy;

import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.TipoTransacao;

public interface TransacaoStrategy {
    void realizarTransacao(AbstractConta conta, TransacaoDTO transacaoDTO);
    TipoTransacao getTipoTransacao();
}

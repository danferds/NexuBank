package com.finance.nexubank.strategy;

import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.TipoTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransacaoContext {

    private final Map<TipoTransacao, TransacaoStrategy> strategies = new HashMap<>();

    @Autowired
    public TransacaoContext(List<TransacaoStrategy> strategyList) {
        for (TransacaoStrategy strategy : strategyList) {
            strategies.put(strategy.getTipoTransacao(), strategy);
        }
    }

    public void realizarTransacao(AbstractConta conta, TransacaoDTO transacaoDTO) {
        TipoTransacao tipo = transacaoDTO.getTipo();
        TransacaoStrategy strategy = strategies.get(tipo);
        if (strategy == null) {
            throw new IllegalArgumentException("Estratégia de transação não encontrada para: " + tipo);
        }
        strategy.realizarTransacao(conta, transacaoDTO);
    }
}

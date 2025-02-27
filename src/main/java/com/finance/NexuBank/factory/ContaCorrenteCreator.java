package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaCorrente;
import com.finance.nexubank.model.StatusConta;
import com.finance.nexubank.model.TipoConta;
import com.finance.nexubank.utils.ContaUtils;
import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteCreator implements ContaCreator {

    @Override
    public AbstractConta criarConta(ContaCriacaoDTO dto) {
        ContaCorrente conta = new ContaCorrente();
        conta.setAgencia(dto.getAgencia());
        conta.setNumeroConta(ContaUtils.gerarNumeroConta(dto.getAgencia()));
        conta.setStatus(StatusConta.ATIVA);
        conta.setSaldo(0.0);
        conta.setLimiteChequeEspecial(dto.getLimiteChequeEspecial() != null ? dto.getLimiteChequeEspecial() : 0.0);
        conta.setLimiteTransacaoDiaria(dto.getLimiteTransacaoDiaria() != null ? dto.getLimiteTransacaoDiaria() : 0.0);
        return conta;
    }

    @Override
    public TipoConta getTipoConta() {
        return TipoConta.CORRENTE;
    }
}

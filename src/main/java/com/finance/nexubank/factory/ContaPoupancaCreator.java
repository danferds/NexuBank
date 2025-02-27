package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaPoupanca;
import com.finance.nexubank.model.StatusConta;
import com.finance.nexubank.model.TipoConta;
import com.finance.nexubank.utils.ContaUtils;
import org.springframework.stereotype.Component;

@Component
public class ContaPoupancaCreator implements ContaCreator {

    @Override
    public AbstractConta criarConta(ContaCriacaoDTO dto) {
        ContaPoupanca conta = new ContaPoupanca();
        conta.setAgencia(dto.getAgencia());
        conta.setNumeroConta(ContaUtils.gerarNumeroConta(dto.getAgencia()));
        conta.setStatus(StatusConta.ATIVA);
        conta.setSaldo(0.0);
        conta.setTaxaRendimentoMensal(dto.getTaxaRendimentoMensal() != null ? dto.getTaxaRendimentoMensal() : 0.0);

        return conta;
    }

    @Override
    public TipoConta getTipoConta() {
        return TipoConta.POUPANCA;
    }
}

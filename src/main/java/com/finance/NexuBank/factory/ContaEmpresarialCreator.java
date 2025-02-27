package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaEmpresarial;
import com.finance.nexubank.model.StatusConta;
import com.finance.nexubank.model.TipoConta;
import com.finance.nexubank.utils.ContaUtils;
import org.springframework.stereotype.Component;

@Component
public class ContaEmpresarialCreator implements ContaCreator {

    @Override
    public AbstractConta criarConta(ContaCriacaoDTO dto) {
        ContaEmpresarial conta = new ContaEmpresarial();
        conta.setAgencia(dto.getAgencia());
        conta.setNumeroConta(ContaUtils.gerarNumeroConta(dto.getAgencia()));
        conta.setStatus(StatusConta.ATIVA);
        conta.setSaldo(0.0);
        conta.setRazaoSocial(dto.getRazaoSocial());
        conta.setLimiteCredito(dto.getLimiteCredito() != null ? dto.getLimiteCredito() : 0.0);
        conta.setLimiteUsuariosAutorizados(dto.getLimiteUsuariosAutorizados() != null ? dto.getLimiteUsuariosAutorizados() : 0);
        return conta;
    }

    @Override
    public TipoConta getTipoConta() {
        return TipoConta.EMPRESARIAL;
    }
}

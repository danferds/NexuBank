package com.finance.nexubank.service;

import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaCorrente;
import com.finance.nexubank.model.ContaEmpresarial;
import com.finance.nexubank.utils.ContaCorrenteUtils;
import com.finance.nexubank.utils.ContaEmpresarialUtils;
import org.springframework.stereotype.Service;

@Service
public class LimiteService {

    /**
     * Atualiza os limites da conta com base na renda mensal do cliente.
     * Para contas correntes, atualiza o limite do cheque especial (20% da renda).
     * Para contas empresariais, atualiza o limite de crédito (40% da renda).
     *
     * @param conta       a conta a ser atualizada
     * @param rendaMensal a renda mensal do cliente
     */
    public void atualizarLimites(AbstractConta conta, double rendaMensal) {
        if (conta instanceof ContaCorrente) {
            ContaCorrenteUtils.atualizarLimiteChequeEspecial(conta, rendaMensal);
        } else if (conta instanceof ContaEmpresarial) {
            ContaEmpresarialUtils.atualizarLimiteCredito(conta, rendaMensal);
        }
    }
}

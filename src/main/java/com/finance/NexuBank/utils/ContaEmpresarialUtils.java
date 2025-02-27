package com.finance.nexubank.utils;

import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaEmpresarial;

public class ContaEmpresarialUtils {

    /**
     * Atualiza o limite de crédito para contas empresariais.
     * Regra: Limite de crédito = 40% da renda mensal do cliente.
     *
     * @param conta a conta que deve ser do tipo ContaEmpresarial.
     * @param rendaMensal a renda mensal do cliente.
     */
    public static void atualizarLimiteCredito(AbstractConta conta, double rendaMensal) {
        if (conta instanceof ContaEmpresarial) {
            double limiteCredito = rendaMensal * 0.4;
            ((ContaEmpresarial) conta).setLimiteCredito(limiteCredito);
        }
    }
}
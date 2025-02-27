package com.finance.nexubank.utils;

import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ContaCorrente;

public class ContaCorrenteUtils {

    /**
     * Atualiza o limite do cheque especial para contas correntes, calculando 20% da renda mensal.
     *
     * @param conta       a conta que poderá ser uma ContaCorrente
     * @param rendaMensal a renda mensal do cliente
     */
    public static void atualizarLimiteChequeEspecial(AbstractConta conta, double rendaMensal) {
        if (conta instanceof ContaCorrente) {
            double limiteCheque = ContaCalculadoraUtils.calcularLimiteChequeEspecial(rendaMensal);
            ((ContaCorrente) conta).setLimiteChequeEspecial(limiteCheque);
        }
    }
}

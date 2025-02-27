package com.finance.nexubank.utils;

import com.finance.nexubank.utils.Constants;

public class ContaPoupancaUtils {

    /**
     * Calcula o rendimento mensal para uma conta poupança utilizando a taxa definida em Constants.
     * Regra: rendimento = saldo * (TAXA_RENDIMENTO_POUPANCA / 100)
     *
     * @param saldo o saldo atual da conta poupança
     * @return o rendimento calculado
     */
    public static double calcularRendimentoMensal(double saldo) {
        return saldo * (Constants.TAXA_RENDIMENTO_POUPANCA / 100.0);
    }
}

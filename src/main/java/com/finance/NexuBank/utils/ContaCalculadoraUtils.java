package com.finance.nexubank.utils;

public class ContaCalculadoraUtils {

    /**
     * Calcula o limite do cheque especial com base na renda mensal.
     * O limite é 20% da renda.
     *
     * @param rendaMensal a renda mensal do cliente
     * @return o limite do cheque especial
     */
    public static double calcularLimiteChequeEspecial(double rendaMensal) {
        return rendaMensal * 0.2;
    }
}

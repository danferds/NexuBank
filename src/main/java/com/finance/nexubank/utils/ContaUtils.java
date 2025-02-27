package com.finance.nexubank.utils;

public class ContaUtils {
    public static String gerarNumeroConta(String agencia) {
        int numero = (int)(Math.random() * 90000) + 10000;
        int digito = (int)(Math.random() * 9);
        return numero + "-" + digito;
    }
}
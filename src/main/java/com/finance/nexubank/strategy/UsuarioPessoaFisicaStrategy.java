package com.finance.nexubank.strategy;

import org.springframework.stereotype.Component;

@Component
public class UsuarioPessoaFisicaStrategy implements UsuarioStrategy {

    @Override
    public boolean isValid(String documento) {
        if (documento == null) {
            return false;
        }
        int length = documento.replaceAll("\\D", "").length();
        return length == 11;
    }
}

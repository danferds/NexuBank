package com.finance.nexubank.factory;

import com.finance.nexubank.model.ChavePixTipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChavePixFactory {

    private final ChavePixCpfCreator cpfCreator;
    private final ChavePixCnpjCreator cnpjCreator;
    private final ChavePixEmailCreator emailCreator;
    private final ChavePixTelefoneCreator telefoneCreator;
    private final ChavePixAleatoriaCreator aleatoriaCreator;

    @Autowired
    public ChavePixFactory(ChavePixCpfCreator cpfCreator,
                           ChavePixCnpjCreator cnpjCreator,
                           ChavePixEmailCreator emailCreator,
                           ChavePixTelefoneCreator telefoneCreator,
                           ChavePixAleatoriaCreator aleatoriaCreator) {
        this.cpfCreator = cpfCreator;
        this.cnpjCreator = cnpjCreator;
        this.emailCreator = emailCreator;
        this.telefoneCreator = telefoneCreator;
        this.aleatoriaCreator = aleatoriaCreator;
    }

    public ChavePixCreator getCreator(ChavePixTipo tipo) {
        switch (tipo) {
            case CPF:
                return cpfCreator;
            case CNPJ:
                return cnpjCreator;
            case EMAIL:
                return emailCreator;
            case TELEFONE:
                return telefoneCreator;
            case ALEATORIA:
                return aleatoriaCreator;
            default:
                throw new IllegalArgumentException("Tipo de chave Pix inválido: " + tipo);
        }
    }
}

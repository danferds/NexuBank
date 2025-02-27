package com.finance.nexubank.resolver;

import com.finance.nexubank.factory.ContaCreator;
import com.finance.nexubank.model.TipoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContaFactoryResolver {

    private final Map<TipoConta, ContaCreator> contaCreators;

    @Autowired
    public ContaFactoryResolver(Map<TipoConta, ContaCreator> contaCreators) {
        this.contaCreators = contaCreators;
    }

    public ContaCreator resolve(TipoConta tipoConta) {
        ContaCreator creator = contaCreators.get(tipoConta);
        if (creator == null) {
            throw new IllegalArgumentException("Fábrica não encontrada para o tipo de conta: " + tipoConta);
        }
        return creator;
    }
}
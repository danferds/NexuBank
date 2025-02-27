package com.finance.nexubank.config;

import com.finance.nexubank.factory.ContaCreator;
import com.finance.nexubank.model.TipoConta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ContaFactoryConfig {

    @Autowired
    private List<ContaCreator> factories;

    @Bean
    public Map<TipoConta, ContaCreator> contaFactories() {
        return factories.stream().collect(Collectors.toMap(ContaCreator::getTipoConta, factory -> factory));
    }
}
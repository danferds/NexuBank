package com.finance.nexubank.factory;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ChavePixAleatoriaCreator implements ChavePixCreator {
    @Override
    public String criarChave(ChavePixCriacaoDTO dto, AbstractConta conta) {
        return UUID.randomUUID().toString();
    }
}
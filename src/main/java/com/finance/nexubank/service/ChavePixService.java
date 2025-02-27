package com.finance.nexubank.service;

import com.finance.nexubank.factory.ChavePixCreator;
import com.finance.nexubank.factory.ChavePixFactory;
import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.dto.ChavePixResponseDTO;
import com.finance.nexubank.exception.ChavePixLimiteExcedidoException;
import com.finance.nexubank.exception.ContaNotFoundException;
import com.finance.nexubank.mapper.ChavePixMapper;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.ChavePix;
import com.finance.nexubank.repository.ChavePixRepository;
import com.finance.nexubank.repository.ContaRepository;
import com.finance.nexubank.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ChavePixService {

    private static final Logger logger = LoggerFactory.getLogger(ChavePixService.class);

    private final ContaRepository contaRepository;
    private final ChavePixRepository chavePixRepository;
    private final ChavePixFactory chavePixCreatorFactory;

    public ChavePixService(ContaRepository contaRepository,
                           ChavePixRepository chavePixRepository,
                           ChavePixFactory chavePixCreatorFactory) {
        this.contaRepository = contaRepository;
        this.chavePixRepository = chavePixRepository;
        this.chavePixCreatorFactory = chavePixCreatorFactory;
    }

    @Transactional
    public ChavePixResponseDTO cadastrarChavePix(UUID contaId, ChavePixCriacaoDTO dto) {
        AbstractConta conta = contaRepository.findById(contaId)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));

        if (conta.getChavesPix().size() >= Constants.LIMITE_CHAVES_PIX) {
            throw new ChavePixLimiteExcedidoException("Limite de chaves Pix atingido para essa conta");
        }

        ChavePixCreator creator = chavePixCreatorFactory.getCreator(dto.getTipo());
        String valorChave = creator.criarChave(dto, conta);
        ChavePix chavePix = ChavePixMapper.toEntity(dto);

        chavePix.setValor(valorChave);
        chavePix.setConta(conta);

        conta.getChavesPix().add(chavePix);
        contaRepository.save(conta);

        logger.info("Chave Pix cadastrada com sucesso para a conta {}", conta.getNumeroConta());

        return ChavePixMapper.toResponseDTO(chavePix);
    }

    @Transactional
    public void removerChavePix(UUID chavePixId) {
        chavePixRepository.deleteById(chavePixId);
        logger.info("Chave Pix {} removida com sucesso", chavePixId);
    }
}
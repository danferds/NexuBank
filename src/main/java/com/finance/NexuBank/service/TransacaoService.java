package com.finance.nexubank.service;

import com.finance.nexubank.dto.PixTransferenciaDTO;
import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.dto.TransacaoResponseDTO;
import com.finance.nexubank.dto.TransacaoListagemDTO;
import com.finance.nexubank.exception.ContaNotFoundException;
import com.finance.nexubank.mapper.TransacaoMapper;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.Transacao;
import com.finance.nexubank.model.TipoTransacao;
import com.finance.nexubank.repository.ChavePixRepository;
import com.finance.nexubank.repository.ContaRepository;
import com.finance.nexubank.strategy.TransacaoContext;
import com.finance.nexubank.validation.PixTransferValidationChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);

    private final ContaRepository contaRepository;
    private final TransacaoContext transacaoContext;
    private final ChavePixRepository chavePixRepository;

    public TransacaoService(ContaRepository contaRepository,
                            TransacaoContext transacaoContext,
                            ChavePixRepository chavePixRepository) {
        this.contaRepository = contaRepository;
        this.transacaoContext = transacaoContext;
        this.chavePixRepository = chavePixRepository;
    }

    @Transactional
    public TransacaoResponseDTO realizarTransacao(UUID id, TransacaoDTO transacaoDTO) {
        AbstractConta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));

        if (transacaoDTO.getTipo() == TipoTransacao.PIX &&
                "TRANSFERENCIA".equalsIgnoreCase(transacaoDTO.getOperacao())) {
            validarTransferenciaPix(conta, transacaoDTO);
        }

        transacaoContext.realizarTransacao(conta, transacaoDTO);

        Transacao novaTransacao = registrarTransacao(conta, transacaoDTO);
        contaRepository.save(conta);

        logger.info("Transação {} de valor {} realizada na conta {}",
                transacaoDTO.getTipo(), transacaoDTO.getValor(), conta.getNumeroConta());

        return TransacaoMapper.toResponseDTO(novaTransacao, conta.getNumeroConta());
    }

    private void validarTransferenciaPix(AbstractConta conta, TransacaoDTO transacaoDTO) {
        if (!(transacaoDTO instanceof PixTransferenciaDTO)) {
            throw new IllegalArgumentException("Para transferência PIX, a chave de destino deve ser informada.");
        }
        PixTransferenciaDTO pixDTO = (PixTransferenciaDTO) transacaoDTO;
        try {
            PixTransferValidationChain.validate(conta, pixDTO, chavePixRepository);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Transacao registrarTransacao(AbstractConta conta, TransacaoDTO transacaoDTO) {
        Transacao transacao = TransacaoMapper.toEntity(transacaoDTO);
        conta.getTransacoes().add(transacao);
        return transacao;
    }

    @Transactional(readOnly = true)
    public List<TransacaoListagemDTO> listarTransacoes(UUID id,
                                                       LocalDateTime dataInicial, LocalDateTime dataFinal, String tipoTransacao) {
        AbstractConta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNotFoundException("Conta não encontrada"));

        return conta.getTransacoes().stream()
                .filter(t -> dataInicial == null || !t.getData().isBefore(dataInicial))
                .filter(t -> dataFinal == null || !t.getData().isAfter(dataFinal))
                .filter(t -> tipoTransacao == null || tipoTransacao.trim().isEmpty() ||
                        t.getTipoTransacao().name().equalsIgnoreCase(tipoTransacao))
                .map(TransacaoMapper::toListagemDTO)
                .collect(Collectors.toList());
    }
}

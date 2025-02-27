package com.finance.nexubank.strategy;

import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.TipoTransacao;
import com.finance.nexubank.observer.ContaEvent;
import com.finance.nexubank.observer.EventType;
import com.finance.nexubank.service.NotificacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class PixTransacaoStrategy implements TransacaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(PixTransacaoStrategy.class);
    private final NotificacaoService notificacaoService;

    public PixTransacaoStrategy(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Override
    public void realizarTransacao(AbstractConta conta, TransacaoDTO transacaoDTO) {
        LocalDateTime now = LocalDateTime.now();
        String operacao = transacaoDTO.getOperacao().toUpperCase();
        switch (operacao) {
            case "TRANSFERENCIA":
                processarTransferencia(conta, transacaoDTO, now);
                break;
            case "RECEBIMENTO":
                processarRecebimento(conta, transacaoDTO, now);
                break;
            default:
                throw new IllegalArgumentException("Operação PIX inválida: " + transacaoDTO.getOperacao());
        }
    }

    private void processarTransferencia(AbstractConta conta, TransacaoDTO transacaoDTO, LocalDateTime now) {

        conta.setSaldo(conta.getSaldo() - transacaoDTO.getValor());
        ContaEvent evento = new ContaEvent(EventType.TRANSFERENCIA_PIX, transacaoDTO.getValor(), now, "Transferência PIX realizada (origem)");
        notificacaoService.notificar(evento, conta);
        logger.info("Transferência PIX de {} realizada na conta {} (origem). Novo saldo: {}",
                transacaoDTO.getValor(), conta.getNumeroConta(), conta.getSaldo());
    }

    private void processarRecebimento(AbstractConta conta, TransacaoDTO transacaoDTO, LocalDateTime now) {
        conta.setSaldo(conta.getSaldo() + transacaoDTO.getValor());
        ContaEvent evento = new ContaEvent(EventType.RECEBIMENTO_PIX, transacaoDTO.getValor(), now, "Recebimento de transferência PIX");
        notificacaoService.notificar(evento, conta);
        logger.info("Recebimento de PIX de {} realizado na conta {}. Novo saldo: {}",
                transacaoDTO.getValor(), conta.getNumeroConta(), conta.getSaldo());
    }

    @Override
    public TipoTransacao getTipoTransacao() {
        return TipoTransacao.PIX;
    }
}
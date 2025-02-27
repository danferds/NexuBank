package com.finance.nexubank.strategy;

import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.model.TipoTransacao;
import com.finance.nexubank.observer.ContaEvent;
import com.finance.nexubank.observer.EventType;
import com.finance.nexubank.service.NotificacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class CartaoTransacaoStrategy implements TransacaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(CartaoTransacaoStrategy.class);
    private final NotificacaoService notificacaoService;

    @Autowired
    public CartaoTransacaoStrategy(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Override
    public void realizarTransacao(AbstractConta conta, TransacaoDTO transacaoDTO) {
        LocalDateTime now = LocalDateTime.now();
        switch (transacaoDTO.getOperacao().toUpperCase()) {
            case "COMPRA":
                processarCompra(conta, transacaoDTO, now);
                break;
            case "PAGAMENTO":
            case "PAGAMENTO DE FATURA":
                processarPagamento(conta, transacaoDTO, now);
                break;
            default:
                throw new IllegalArgumentException("Operação de cartão inválida: " + transacaoDTO.getOperacao());
        }
    }

    private void processarCompra(AbstractConta conta, TransacaoDTO transacaoDTO, LocalDateTime now) {
        if (conta.getSaldo() < transacaoDTO.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente para compra");
        }
        conta.setSaldo(conta.getSaldo() - transacaoDTO.getValor());
        ContaEvent evento = new ContaEvent(EventType.COMPRA_CARTAO, transacaoDTO.getValor(), now, "Compra no cartão realizada");
        notificacaoService.notificar(evento, conta);
        logger.info("Compra de {} realizada na conta {}. Novo saldo: {}",
                transacaoDTO.getValor(), conta.getNumeroConta(), conta.getSaldo());
    }

    private void processarPagamento(AbstractConta conta, TransacaoDTO transacaoDTO, LocalDateTime now) {
        conta.setSaldo(conta.getSaldo() + transacaoDTO.getValor());
        ContaEvent evento = new ContaEvent(EventType.PAGAMENTO_FATURA, transacaoDTO.getValor(), now, "Pagamento de fatura realizado");
        notificacaoService.notificar(evento, conta);
        logger.info("Pagamento de {} realizado na conta {}. Novo saldo: {}",
                transacaoDTO.getValor(), conta.getNumeroConta(), conta.getSaldo());
    }

    @Override
    public TipoTransacao getTipoTransacao() {
        return TipoTransacao.CARTAO;
    }
}
package com.finance.nexubank.service;

import com.finance.nexubank.model.AbstractConta;
import com.finance.nexubank.observer.ContaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    private static final Logger logger = LoggerFactory.getLogger(NotificacaoService.class);

    public void notificar(ContaEvent event, AbstractConta conta) {
        logger.info("Notificação: {} para a conta {}. Valor: {}. Data: {}. Mensagem: {}",
                event.getEventType(), conta.getNumeroConta(), event.getValor(), event.getData(), event.getMensagem());
    }
}

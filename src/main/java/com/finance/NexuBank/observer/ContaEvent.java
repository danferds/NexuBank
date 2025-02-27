package com.finance.nexubank.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ContaEvent {
    private EventType eventType;
    private double valor;
    private LocalDateTime data;
    private String mensagem;
}
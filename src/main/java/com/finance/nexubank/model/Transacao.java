package com.finance.nexubank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "transacao_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID transacaoId;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    private String operacao;
    private double valor;
    private LocalDateTime data;

    @PrePersist
    protected void onCreate() {
        data = LocalDateTime.now();
    }
}
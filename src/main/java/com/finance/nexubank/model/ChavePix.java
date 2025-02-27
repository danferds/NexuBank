package com.finance.nexubank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "chaves_pix")
@Getter
@Setter
@NoArgsConstructor
public class ChavePix {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "chave_pix_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChavePixTipo tipo;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "conta_id", nullable = false)
    private AbstractConta conta;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
    }
}
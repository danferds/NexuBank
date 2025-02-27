package com.finance.nexubank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "conta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta")
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractConta {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "conta_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID contaId;

    @Column(unique = true)
    private String numeroConta;

    private String agencia;
    private double saldo;

    @Enumerated(EnumType.STRING)
    private StatusConta status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private AbstractUsuario usuario;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transacao> transacoes = new ArrayList<>();

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChavePix> chavesPix = new ArrayList<>();
}
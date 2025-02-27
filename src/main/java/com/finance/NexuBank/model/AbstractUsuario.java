package com.finance.nexubank.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "usuario_tipo")
@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractUsuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "usuario_id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID usuarioId;

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private double rendaMensal;

    public abstract String getDocumento();
}
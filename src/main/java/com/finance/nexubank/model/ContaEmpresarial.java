package com.finance.nexubank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("EMPRESARIAL")
@Getter
@Setter
@NoArgsConstructor
public class ContaEmpresarial extends AbstractConta {
    private String razaoSocial;
    private double limiteCredito;
    private int limiteUsuariosAutorizados;
}
package com.finance.nexubank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CORRENTE")
@Getter
@Setter
@NoArgsConstructor
public class ContaCorrente extends AbstractConta {
    private double limiteChequeEspecial;
    private double limiteTransacaoDiaria;
}
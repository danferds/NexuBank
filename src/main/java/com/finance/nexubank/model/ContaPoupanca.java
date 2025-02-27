package com.finance.nexubank.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("POUPANCA")
@Getter
@Setter
@NoArgsConstructor
public class ContaPoupanca extends AbstractConta {
    private double taxaRendimentoMensal;
}

package com.finance.nexubank.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PF")
@Getter
@Setter
@NoArgsConstructor
public class PessoaFisica extends AbstractUsuario {

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Override
    public String getDocumento() {
        return cpf;
    }
}
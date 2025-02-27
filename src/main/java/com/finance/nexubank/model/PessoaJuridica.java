package com.finance.nexubank.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("PJ")
@Getter
@Setter
@NoArgsConstructor
public class PessoaJuridica extends AbstractUsuario {

    @Column(name = "cnpj", unique = true)
    private String cnpj;

    @Override
    public String getDocumento() {
        return cnpj;
    }
}
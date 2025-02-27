package com.finance.nexubank.factory;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.model.AbstractUsuario;
import com.finance.nexubank.strategy.UsuarioPessoaFisicaStrategy;
import com.finance.nexubank.strategy.UsuarioPessoaJuridicaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UsuarioFactory implements UsuarioCreator {

    private final UsuarioPessoaFisicaCreator pessoaFisicaFactory;
    private final UsuarioPessoaJuridicaCreator pessoaJuridicaFactory;
    private final UsuarioPessoaFisicaStrategy pessoaFisica;
    private final UsuarioPessoaJuridicaStrategy pessoaJuridica;

    @Autowired
    public UsuarioFactory(UsuarioPessoaFisicaCreator pessoaFisicaFactory, UsuarioPessoaJuridicaCreator pessoaJuridicaFactory,
                          UsuarioPessoaFisicaStrategy pessoaFisica,
                          UsuarioPessoaJuridicaStrategy pessoaJudirica) {
        this.pessoaFisicaFactory = pessoaFisicaFactory;
        this.pessoaJuridicaFactory = pessoaJuridicaFactory;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJudirica;
    }

    @Override
    public AbstractUsuario createUsuario(UsuarioCriacaoDTO dto) {
        if (dto.getDocumento() != null) {
            String documento = dto.getDocumento();
            if (pessoaFisica.isValid(documento)) {
                return pessoaFisicaFactory.createUsuario(dto);
            } else if (pessoaJuridica.isValid(documento)) {
                return pessoaJuridicaFactory.createUsuario(dto);
            }
        }
        throw new IllegalArgumentException("Documento inválido");
    }
}
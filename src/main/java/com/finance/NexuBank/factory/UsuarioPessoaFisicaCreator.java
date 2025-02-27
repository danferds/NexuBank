package com.finance.nexubank.factory;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.model.PessoaFisica;
import com.finance.nexubank.model.AbstractUsuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPessoaFisicaCreator implements UsuarioCreator {

    @Override
    public AbstractUsuario createUsuario(UsuarioCriacaoDTO dto) {
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.getNome());
        pf.setEmail(dto.getEmail());
        pf.setTelefone(dto.getTelefone());
        pf.setRendaMensal(dto.getRendaMensal());
        pf.setCpf(dto.getDocumento());
        return pf;
    }
}

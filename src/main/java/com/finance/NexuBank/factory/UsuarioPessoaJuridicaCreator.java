package com.finance.nexubank.factory;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.model.PessoaJuridica;
import com.finance.nexubank.model.AbstractUsuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioPessoaJuridicaCreator implements UsuarioCreator {

    @Override
    public AbstractUsuario createUsuario(UsuarioCriacaoDTO dto) {
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNome(dto.getNome());
        pj.setEmail(dto.getEmail());
        pj.setTelefone(dto.getTelefone());
        pj.setRendaMensal(dto.getRendaMensal());
        pj.setCnpj(dto.getDocumento());
        return pj;
    }
}

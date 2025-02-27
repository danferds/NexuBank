package com.finance.nexubank.mapper;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.dto.UsuarioAtualizacaoDTO;
import com.finance.nexubank.dto.UsuarioResponseDTO;
import com.finance.nexubank.factory.UsuarioCreator;
import com.finance.nexubank.model.AbstractUsuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO mapearRespostaDTO(AbstractUsuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setUsuarioId(usuario.getUsuarioId());
        dto.setNome(usuario.getNome());
        dto.setDocumento(usuario.getDocumento());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setRendaMensal(usuario.getRendaMensal());
        return dto;
    }

    public static AbstractUsuario mapearCriacaoDTO(UsuarioCriacaoDTO dto, UsuarioCreator fabricaUsuario) {
        return fabricaUsuario.createUsuario(dto);
    }

    public static void mapearAtualizacaoDTO(UsuarioAtualizacaoDTO dto, AbstractUsuario usuario) {
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setRendaMensal(dto.getRendaMensal());
    }
}

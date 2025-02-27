package com.finance.nexubank.finder;

import com.finance.nexubank.exception.UsuarioNotFoundException;
import com.finance.nexubank.model.AbstractUsuario;
import com.finance.nexubank.repository.UsuarioRepository;
import com.finance.nexubank.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UsuarioFinder {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioFinder(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public AbstractUsuario findByDocumento(String documento) {
        String documentoLimpo = documento.replaceAll("\\D", "");
        if (documentoLimpo.length() == Constants.CPF_LENGTH) {
            return usuarioRepository.findByCpf(documento)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado pelo CPF"));
        } else if (documentoLimpo.length() == Constants.CNPJ_LENGTH) {
            return usuarioRepository.findByCnpj(documento)
                    .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado pelo CNPJ"));
        } else {
            throw new IllegalArgumentException("Documento inválido. Informe um CPF ou CNPJ válido.");
        }
    }

    public AbstractUsuario findById(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuário não encontrado com id: " + id));
    }
}
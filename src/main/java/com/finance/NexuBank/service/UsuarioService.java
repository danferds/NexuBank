package com.finance.nexubank.service;

import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.dto.UsuarioAtualizacaoDTO;
import com.finance.nexubank.dto.UsuarioResponseDTO;
import com.finance.nexubank.exception.TransacoesPendentesException;
import com.finance.nexubank.factory.UsuarioCreator;
import com.finance.nexubank.mapper.UsuarioMapper;
import com.finance.nexubank.model.AbstractUsuario;
import com.finance.nexubank.finder.UsuarioFinder;
import com.finance.nexubank.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final UsuarioCreator fabricaUsuario;
    private final UsuarioFinder usuarioFinder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          UsuarioCreator fabricaUsuario,
                          UsuarioFinder usuarioFinder) {
        this.usuarioRepository = usuarioRepository;
        this.fabricaUsuario = fabricaUsuario;
        this.usuarioFinder = usuarioFinder;
    }

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioCriacaoDTO dto) {
        AbstractUsuario usuario = UsuarioMapper.mapearCriacaoDTO(dto, fabricaUsuario);
        usuarioRepository.save(usuario);
        logger.info("Usuário {} cadastrado com sucesso", usuario.getNome());
        return UsuarioMapper.mapearRespostaDTO(usuario);
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioAtualizacaoDTO dto) {
        AbstractUsuario usuario = usuarioFinder.findById(id);
        UsuarioMapper.mapearAtualizacaoDTO(dto, usuario);

        usuarioRepository.save(usuario);

        logger.info("Usuário {} atualizado com sucesso", usuario.getNome());
        return UsuarioMapper.mapearRespostaDTO(usuario);
    }

    @Transactional
    public void removerUsuario(UUID id) {
        AbstractUsuario usuario = usuarioFinder.findById(id);

        if (usuarioRepository.hasPendingTransactions(usuario)) {
            throw new TransacoesPendentesException("Usuário possui transações pendentes");
        }

        usuarioRepository.delete(usuario);
        logger.info("Usuário removido com sucesso");
    }
}
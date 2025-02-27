package com.finance.nexubank.controller;

import com.finance.nexubank.dto.ApiResponseDTO;
import com.finance.nexubank.dto.UsuarioCriacaoDTO;
import com.finance.nexubank.dto.UsuarioAtualizacaoDTO;
import com.finance.nexubank.dto.UsuarioResponseDTO;
import com.finance.nexubank.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> cadastrarUsuario(@Valid @RequestBody UsuarioCriacaoDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.cadastrarUsuario(dto);
        ApiResponseDTO<UsuarioResponseDTO> response = new ApiResponseDTO<>("success", "Usuário cadastrado com sucesso", resposta);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<UsuarioResponseDTO>> atualizarUsuario(@PathVariable UUID id,
                                                                               @Valid @RequestBody UsuarioAtualizacaoDTO dto) {
        UsuarioResponseDTO resposta = usuarioService.atualizarUsuario(id, dto);
        ApiResponseDTO<UsuarioResponseDTO> response = new ApiResponseDTO<>("success", "Usuário atualizado com sucesso", resposta);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> removerUsuario(@PathVariable UUID id) {
        usuarioService.removerUsuario(id);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>("success", "Usuário removido com sucesso", null);

        return ResponseEntity.ok(response);
    }
}
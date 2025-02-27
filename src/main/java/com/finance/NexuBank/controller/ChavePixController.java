package com.finance.nexubank.controller;

import com.finance.nexubank.dto.ApiResponseDTO;
import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.dto.ChavePixResponseDTO;
import com.finance.nexubank.service.ChavePixService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/chavepix")
public class ChavePixController {

    private final ChavePixService chavePixService;

    @Autowired
    public ChavePixController(ChavePixService chavePixService) {
        this.chavePixService = chavePixService;
    }

    @PostMapping("/{contaId}")
    public ResponseEntity<ApiResponseDTO<ChavePixResponseDTO>> cadastrarChavePix(
            @PathVariable UUID contaId,
            @Valid @RequestBody ChavePixCriacaoDTO dto) {
        ChavePixResponseDTO response = chavePixService.cadastrarChavePix(contaId, dto);
        ApiResponseDTO<ChavePixResponseDTO> apiResponse =
                new ApiResponseDTO<>("success", "Chave Pix cadastrada com sucesso", response);

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{chavePixId}")
    public ResponseEntity<ApiResponseDTO<Void>> removerChavePix(@PathVariable UUID chavePixId) {
        chavePixService.removerChavePix(chavePixId);
        ApiResponseDTO<Void> response = new ApiResponseDTO<>("success", "Chave Pix removida com sucesso", null);

        return ResponseEntity.ok(response);
    }
}
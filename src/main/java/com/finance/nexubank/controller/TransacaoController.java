package com.finance.nexubank.controller;

import com.finance.nexubank.dto.ApiResponseDTO;
import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.dto.TransacaoListagemDTO;
import com.finance.nexubank.dto.TransacaoResponseDTO;
import com.finance.nexubank.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @Autowired
    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<TransacaoResponseDTO>> realizarTransacao(
            @PathVariable UUID id,
            @Valid @RequestBody TransacaoDTO transacaoDTO) {

        TransacaoResponseDTO transacaoResponse = transacaoService.realizarTransacao(id, transacaoDTO);
        ApiResponseDTO<TransacaoResponseDTO> response =
                new ApiResponseDTO<>("success", "Transação realizada com sucesso", transacaoResponse);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/historico")
    public ResponseEntity<ApiResponseDTO<List<TransacaoListagemDTO>>> listarHistoricoTransacoes(
            @PathVariable UUID id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestParam(required = false) String tipoTransacao) {

        List<TransacaoListagemDTO> transacoes = transacaoService.listarTransacoes(id, dataInicial, dataFinal, tipoTransacao);
        ApiResponseDTO<List<TransacaoListagemDTO>> response =
                new ApiResponseDTO<>("success", "Histórico de transações consultado com sucesso", transacoes);

        return ResponseEntity.ok(response);
    }
}
package com.finance.nexubank.controller;

import com.finance.nexubank.dto.*;
import com.finance.nexubank.dto.ContaResponseDTO;
import com.finance.nexubank.service.ContaService;
import com.finance.nexubank.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conta")
@Validated
public class ContaController {

    private final ContaService contaService;

    @Autowired
    public ContaController(ContaService contaService, TransacaoService transacaoService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<ContaResponseDTO>> criarConta(@Valid @RequestBody ContaCriacaoDTO dto) {
        ContaResponseDTO contaResposta = contaService.criarConta(dto);
        ApiResponseDTO<ContaResponseDTO> response = new ApiResponseDTO<>("success", "Conta criada com sucesso", contaResposta);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/deposito")
    public ResponseEntity<ApiResponseDTO<DepositoResponseDTO>> depositar(@Valid @RequestBody DepositoDTO depositoDTO) {
        DepositoResponseDTO depositoResponse = contaService.depositar(depositoDTO);
        ApiResponseDTO<DepositoResponseDTO> response = new ApiResponseDTO<>("success", "Depósito realizado com sucesso", depositoResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/rendimento")
    public ResponseEntity<ApiResponseDTO<RendimentoPoupancaResponseDTO>> calcularRendimentoMensal(@PathVariable UUID id) {
        RendimentoPoupancaResponseDTO response = contaService.calcularRendimentoMensalPoupanca(id);
        ApiResponseDTO<RendimentoPoupancaResponseDTO> apiResponse = new ApiResponseDTO<>("success", "Rendimento calculado com sucesso", response);

        return ResponseEntity.ok(apiResponse);
    }
}
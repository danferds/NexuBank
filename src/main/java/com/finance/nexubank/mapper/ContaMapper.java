package com.finance.nexubank.mapper;

import com.finance.nexubank.dto.ContaCriacaoDTO;
import com.finance.nexubank.dto.ContaResponseDTO;
import com.finance.nexubank.factory.ContaCreator;
import com.finance.nexubank.model.AbstractConta;

public class ContaMapper {

    public static ContaResponseDTO mapearRespostaDTO(AbstractConta conta) {
        if (conta == null) {
            return null;
        }

        ContaResponseDTO dto = new ContaResponseDTO();
        dto.setContaId(conta.getContaId().toString());
        dto.setNumeroConta(conta.getNumeroConta());
        dto.setAgencia(conta.getAgencia());
        dto.setSaldo(conta.getSaldo());
        dto.setStatus(conta.getStatus().name());

        String tipoConta;
        String nomeClasse = conta.getClass().getSimpleName().toUpperCase();
        if (nomeClasse.contains("CORRENTE")) {
            tipoConta = "CORRENTE";
        } else if (nomeClasse.contains("POUPANCA")) {
            tipoConta = "POUPANCA";
        } else if (nomeClasse.contains("EMPRESARIAL")) {
            tipoConta = "EMPRESARIAL";
        } else {
            tipoConta = "DESCONHECIDO";
        }
        dto.setTipoConta(tipoConta);
        dto.setDocumentoUsuario(conta.getUsuario() != null ? conta.getUsuario().getDocumento() : null);
        return dto;
    }

    public static AbstractConta mapearCriacaoDTO(ContaCriacaoDTO dto, ContaCreator fabricaConta) {
        return fabricaConta.criarConta(dto);
    }
}
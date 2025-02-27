package com.finance.nexubank.mapper;

import com.finance.nexubank.dto.TransacaoListagemDTO;
import com.finance.nexubank.dto.TransacaoResponseDTO;
import com.finance.nexubank.dto.TransacaoDTO;
import com.finance.nexubank.model.Transacao;

public class TransacaoMapper {

    public static TransacaoResponseDTO toResponseDTO(Transacao transacao, String numeroConta) {
        TransacaoResponseDTO dto = new TransacaoResponseDTO();
        dto.setTipoTransacao(transacao.getTipoTransacao().name());
        dto.setValor(transacao.getValor());
        dto.setNumeroConta(numeroConta);

        return dto;
    }
    public static Transacao toEntity(TransacaoDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setTipoTransacao(dto.getTipo());
        transacao.setOperacao(dto.getOperacao());
        transacao.setValor(dto.getValor());

        return transacao;
    }

    public static TransacaoListagemDTO toListagemDTO(Transacao transacao) {
        TransacaoListagemDTO dto = new TransacaoListagemDTO();
        dto.setTipoTransacao(transacao.getTipoTransacao().name());
        dto.setOperacao(transacao.getOperacao());
        dto.setValor(transacao.getValor());
        dto.setData(transacao.getData());
        return dto;
    }
}
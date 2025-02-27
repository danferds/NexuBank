package com.finance.nexubank.mapper;

import com.finance.nexubank.dto.ChavePixCriacaoDTO;
import com.finance.nexubank.dto.ChavePixResponseDTO;
import com.finance.nexubank.model.ChavePix;

public class ChavePixMapper {

    public static ChavePixResponseDTO toResponseDTO(ChavePix chavePix) {
        ChavePixResponseDTO dto = new ChavePixResponseDTO();
        dto.setId(chavePix.getId());
        dto.setValor(chavePix.getValor());
        dto.setTipo(chavePix.getTipo().name());
        dto.setDataCriacao(chavePix.getDataCriacao());

        return dto;
    }

    public static ChavePix toEntity(ChavePixCriacaoDTO dto) {
        ChavePix chavePix = new ChavePix();
        chavePix.setTipo(dto.getTipo());
        chavePix.setValor(dto.getValor());

        return chavePix;
    }
}

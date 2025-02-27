package com.finance.nexubank.dto;

import lombok.Data;

import java.util.UUID;


@Data
public class UsuarioResponseDTO {
    private UUID usuarioId;
    private String nome;
    private String documento;
    private String email;
    private String telefone;
    private double rendaMensal;

}
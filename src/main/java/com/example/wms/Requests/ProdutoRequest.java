package com.example.wms.Requests;

public record ProdutoRequest(
        String serialCode,
        String nome,
        String unidade,
        Double preco,
        Double aproxVolume,
        Long utilizadorCriadorId
) {}

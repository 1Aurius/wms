package com.example.wms.Requests;

public record RequesicaoStockHeaderRequest(
        String descricao,
        Long utilizadorCriadorId
) {}

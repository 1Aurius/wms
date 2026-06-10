package com.example.wms.Requests;

public record VeiculoRequest(
        String matricula,
        Double aproxVolume,
        String ano,
        Long modeloId,
        Long utilizadorCriadorId,
        Long postoId
) {}

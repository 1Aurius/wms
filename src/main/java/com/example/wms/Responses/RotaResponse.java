package com.example.wms.Responses;

public record RotaResponse(
        Integer id,
        String nome,
        String estado,
        Integer veiculoId,
        String veiculoMatricula,
        Integer equipaId,
        String equipaDesignacao
) {}

package com.example.wms.Responses;

public record VeiculoResponse(
        Integer id,
        String matricula,
        Double aproxVolume,
        String ano,
        String modeloDesignacao,
        Integer postoId,
        String postoDesignacao
) {}

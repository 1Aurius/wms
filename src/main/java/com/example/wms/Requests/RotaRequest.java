package com.example.wms.Requests;

public record RotaRequest(
        String nome,
        Integer veiculoId,
        Integer equipaTransporteId,
        Long utilizadorCriadorId,
        String dataPrevista,
        java.util.List<Integer> postosIds
) {}

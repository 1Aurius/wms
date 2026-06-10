package com.example.wms.Requests;

public record RotaStatusRequest(
        String estado,
        Integer veiculoId,
        Integer equipaId
) {}

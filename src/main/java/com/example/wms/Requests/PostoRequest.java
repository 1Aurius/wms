package com.example.wms.Requests;

public record PostoRequest(
        String designacao,
        String local,
        String cp,
        String tipo,
        Integer gerenteId
) {}

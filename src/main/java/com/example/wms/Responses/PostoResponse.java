package com.example.wms.Responses;

import com.example.wms.Models.posto.Posto;

public record PostoResponse(
        Integer id,
        String designacao,
        String local,
        String cp,
        String tipo,
        Integer gerenteId
) {
    public static PostoResponse from(Posto posto) {
        return new PostoResponse(
                posto.getId(),
                posto.getDesignacao(),
                posto.getLocal(),
                posto.getCp(),
                posto.getTipo(),
                Math.toIntExact(posto.getGerentePosto() != null ? posto.getGerentePosto().getId() : null)
        );
    }
}
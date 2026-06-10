package com.example.wms.Responses;

import com.example.wms.Models.requesicaoStock.RequesicaoStock;
import java.time.Instant;

public record RequesicaoStockHeaderResponse(
        Integer id,
        Instant dhRegisto,
        Instant dhValidacao,
        String descricao,
        String estado,
        String utilizadorCriadorUsername,
        String utilizadorValidadorUsername
) {
    public static RequesicaoStockHeaderResponse from(RequesicaoStock rs) {
        return new RequesicaoStockHeaderResponse(
                rs.getId(),
                rs.getDhRegisto(),
                rs.getDhValidacao(),
                rs.getDescricao(),
                rs.getEstado(),
                rs.getUtilizadorCriador() != null ? rs.getUtilizadorCriador().getUsername() : null,
                rs.getUtilizadorValidador() != null ? rs.getUtilizadorValidador().getUsername() : null
        );
    }
}

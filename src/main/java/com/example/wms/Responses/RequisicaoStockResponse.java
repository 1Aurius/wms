package com.example.wms.Responses;

import com.example.wms.Models.requesicao.Requesicao;

public record RequisicaoStockResponse(
        Integer id,
        Integer requesicaoStockId,
        Integer stockId,
        String productName,
        String postoDesignacao,
        Integer quant
) {
    public static RequisicaoStockResponse from(Requesicao r) {
        return new RequisicaoStockResponse(
                r.getId(),
                r.getRequesicaoStock().getId(),
                r.getStock().getId(),
                r.getStock().getProduto().getNome(),
                r.getStock().getPosto().getDesignacao(),
                r.getQuant()
        );
    }
}
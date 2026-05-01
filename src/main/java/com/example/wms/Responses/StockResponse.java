package com.example.wms.Responses;

import com.example.wms.Models.stock.Stock;

public record StockResponse(
        Integer id,
        Integer produtoId,
        Integer postoId,
        Integer quantidade
) {
    public static StockResponse from(Stock stock) {
        return new StockResponse(
                stock.getId(),
                stock.getProduto().getId(),
                stock.getPosto().getId(),
                stock.getQuantidade()
        );
    }
}

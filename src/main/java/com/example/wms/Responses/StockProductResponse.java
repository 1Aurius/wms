package com.example.wms.Responses;

import com.example.wms.Models.stock.Stock;

public record StockProductResponse(
        Integer stockId,
        Integer productId,
        String productName,
        String serialCode,
        String unidade,
        Double preco,
        Integer quantity,
        Integer postoId,
        String postoDesignacao,
        String postoTipo
) {
    public static StockProductResponse from(Stock stock) {
        return new StockProductResponse(
                stock.getId(),
                stock.getProduto().getId(),
                stock.getProduto().getNome(),
                stock.getProduto().getSerialCode(),
                stock.getProduto().getUnidade(),
                stock.getProduto().getPreco(),
                stock.getQuantidade(),
                stock.getPosto().getId(),
                stock.getPosto().getDesignacao(),
                stock.getPosto().getTipo()
        );
    }
}
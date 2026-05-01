package com.example.wms.Requests;

public record StockRequest(
        Long produtoId,
        Long postoId,
        Integer quantidade
) {}
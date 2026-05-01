package com.example.wms.Requests;

public record RequisicaoStockRequest(
        Long requisicaoId,
        Long stockId,
        Long quant
) {}
package com.example.wms.Responses;

import com.example.wms.Models.produto.Produto;

public record ProdutoResponse(
        Integer id,
        String serialCode,
        String nome,
        String unidade,
        Double preco,
        Double aproxVolume,
        Long utilizadorCriadorId
) {
    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getSerialCode(),
                produto.getNome(),
                produto.getUnidade(),
                produto.getPreco(),
                produto.getAproxVolume(),
                produto.getUtilizadorCriador() != null ? produto.getUtilizadorCriador().getId() : null
        );
    }
}
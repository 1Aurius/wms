package com.example.wms.Services;

import com.example.wms.Models.produto.Produto;
import com.example.wms.Models.produto.ProdutoRepository;
import com.example.wms.Requests.ProdutoRequest;
import com.example.wms.Responses.ProdutoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponse> getAll(String query) {
        return produtoRepository.findAll().stream()
                .filter(p -> query == null || p.getNome().toLowerCase().contains(query.toLowerCase())
                        || p.getSerialCode().toLowerCase().contains(query.toLowerCase()))
                .map(ProdutoResponse::from)
                .toList();
    }

    public ProdutoResponse getById(Long id) {
        return produtoRepository.findById(id)
                .map(ProdutoResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public ProdutoResponse create(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setSerialCode(request.serialCode());
        produto.setNome(request.nome());
        produto.setUnidade(request.unidade());
        produto.setPreco(request.preco());
        produto.setAproxVolume(request.aproxVolume());
        return ProdutoResponse.from(produtoRepository.save(produto));
    }

    public ProdutoResponse update(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        produto.setSerialCode(request.serialCode());
        produto.setNome(request.nome());
        produto.setUnidade(request.unidade());
        produto.setPreco(request.preco());
        produto.setAproxVolume(request.aproxVolume());
        return ProdutoResponse.from(produtoRepository.save(produto));
    }

    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        produtoRepository.deleteById(id);
    }
}

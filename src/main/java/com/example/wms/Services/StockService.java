package com.example.wms.Services;

import com.example.wms.Controllers.ProdutoController;
import com.example.wms.Models.posto.PostoRepository;
import com.example.wms.Models.produto.ProdutoRepository;
import com.example.wms.Models.stock.Stock;
import com.example.wms.Models.stock.StockRepository;
import com.example.wms.Requests.StockRequest;
import com.example.wms.Responses.StockProductResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final ProdutoRepository produtoRepository;
    private final PostoRepository postoRepository;

    public StockService(StockRepository stockRepository, ProdutoRepository produtoRepository, PostoRepository postoRepository) {
        this.stockRepository = stockRepository;
        this.produtoRepository = produtoRepository;
        this.postoRepository = postoRepository;
    }

    public List<StockProductResponse> getProductsInStock(
            String query, Integer postoId, Integer minQty, Integer maxQty, boolean inStockOnly) {

        return stockRepository.findAll().stream()
                .filter(s -> query == null || s.getProduto().getNome().toLowerCase().contains(query.toLowerCase()))
                .filter(s -> postoId == null || s.getPosto().getId().equals(postoId))
                .filter(s -> minQty == null || s.getQuantidade() >= minQty)
                .filter(s -> maxQty == null || s.getQuantidade() <= maxQty)
                .filter(s -> !inStockOnly || s.getQuantidade() > 0)
                .map(StockProductResponse::from)
                .toList();
    }

    public StockProductResponse create(StockRequest request) {
        Stock stock = new Stock();
        stock.setProduto(produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto not found")));
        stock.setPosto(postoRepository.findById(request.postoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto not found")));
        stock.setQuantidade(request.quantidade());
        return StockProductResponse.from(stockRepository.save(stock));
    }

    public StockProductResponse update(Integer id, StockRequest request) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        stock.setProduto(produtoRepository.findById(request.produtoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto not found")));
        stock.setPosto(postoRepository.findById(request.postoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Posto not found")));
        stock.setQuantidade(request.quantidade());
        return StockProductResponse.from(stockRepository.save(stock));
    }

    public void delete(Integer id) {
        if (!stockRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        stockRepository.deleteById(id);
    }
}

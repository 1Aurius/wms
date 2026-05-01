package com.example.wms.Controllers;

import com.example.wms.Requests.StockRequest;
import com.example.wms.Responses.StockProductResponse;
import com.example.wms.Services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<StockProductResponse>> getProductsInStock(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer postoId,
            @RequestParam(required = false) Integer minQty,
            @RequestParam(required = false) Integer maxQty,
            @RequestParam(defaultValue = "true") boolean inStockOnly
    ) {
        return ResponseEntity.ok(stockService.getProductsInStock(query, postoId, minQty, maxQty, inStockOnly));
    }

    @PostMapping
    public ResponseEntity<StockProductResponse> create(@RequestBody StockRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(stockService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockProductResponse> update(@PathVariable Integer id, @RequestBody StockRequest request) {
        return ResponseEntity.ok(stockService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        stockService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
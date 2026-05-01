package com.example.wms.Controllers;

import com.example.wms.Requests.RequisicaoStockRequest;
import com.example.wms.Responses.RequisicaoStockResponse;
import com.example.wms.Services.RequisicaoStockService;
import com.example.wms.Services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requesicoes-stock")
public class RequesicaoStockController {
    private final RequisicaoStockService service;

    public RequesicaoStockController(RequisicaoStockService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RequisicaoStockResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequisicaoStockResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/por-requesicao/{requesicaoStockId}")
    public ResponseEntity<List<RequisicaoStockResponse>> getByRequesicaoStockId(@PathVariable Integer requesicaoStockId) {
        return ResponseEntity.ok(service.getByRequesicaoStockId(requesicaoStockId));
    }
    @PostMapping
    public ResponseEntity<RequisicaoStockResponse> create(@RequestBody RequisicaoStockRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequisicaoStockResponse> update(@PathVariable Long id, @RequestBody RequisicaoStockRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
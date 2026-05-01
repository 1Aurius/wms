package com.example.wms.Controllers;

import com.example.wms.Requests.PostoRequest;
import com.example.wms.Responses.PostoResponse;
import com.example.wms.Services.PostoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postos")
public class PostoController {
    private final PostoService postoService;

    public PostoController(PostoService postoService) {
        this.postoService = postoService;
    }

    @GetMapping
    public ResponseEntity<List<PostoResponse>> getAll() {
        return ResponseEntity.ok(postoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(postoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PostoResponse> create(@RequestBody PostoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postoService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostoResponse> update(@PathVariable Long id, @RequestBody PostoRequest request) {
        return ResponseEntity.ok(postoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
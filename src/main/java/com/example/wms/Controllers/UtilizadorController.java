package com.example.wms.Controllers;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Requests.LoginUtilizadorRequest;
import com.example.wms.Requests.RegistoUtilizadorRequest;
import com.example.wms.Responses.LoginResponse;
import com.example.wms.Responses.UtilizadorResponse;
import com.example.wms.Services.UtilizadorService;
import com.example.wms.Utils.JwtUtil;
import com.example.wms.Utils.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    @Autowired
    private UtilizadorService service;

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistoUtilizadorRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Utilizador novo = service.register(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(UtilizadorResponse.from(novo));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUtilizadorRequest request, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            Utilizador utilizador = service.loadUserByUsername(request.getUsername());
            if (PasswordUtil.verifyPassword(request.getPassword(), utilizador.getPassword())) {
                String token = JwtUtil.generateToken(utilizador.getUsername(), utilizador.isAdmin(), utilizador.isArmazem());
                return ResponseEntity.ok(new LoginResponse(
                        utilizador.getId(),
                        utilizador.getUsername(),
                        token,
                        utilizador.getDn(),
                        utilizador.isAdmin(),
                        utilizador.isGestorRotas(),
                        utilizador.isGestor(),
                        utilizador.isLoja(),
                        utilizador.isArmazem()
                ));
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<UtilizadorResponse>> getAll(
            @RequestParam(required = false) String query) {
        return ResponseEntity.ok(service.getAll(query));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilizadorResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilizadorResponse> update(
            @PathVariable Long id, @RequestBody RegistoUtilizadorRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
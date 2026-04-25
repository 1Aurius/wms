package com.example.wms.Controllers;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Requests.LoginUtilizadorRequest;
import com.example.wms.Requests.RegistoUtilizadorRequest;
import com.example.wms.Services.UtilizadorService;
import com.example.wms.Utils.JwtUtil;
import com.example.wms.Utils.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
            request.setPassword(PasswordUtil.encryptPassword(request.getPassword()));
            Utilizador novo = service.register(request);
            return ResponseEntity.ok(novo);
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
            Utilizador userDetails = (Utilizador) service.loadUserByUsername(request.getUsername());

            if (PasswordUtil.verifyPassword(request.getPassword(), user.getPassword())) {
                // Generate token
                String token = JwtUtil.generateToken(userDetails.getUsername(), userDetails.get());

                // Return only safe data + token
                return ResponseEntity.ok(new LoginResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        token  // ✅ client stores this
                ));
            } else {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body("User not found");
        }

}
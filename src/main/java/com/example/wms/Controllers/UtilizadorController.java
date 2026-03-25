package com.example.wms.Controllers;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Requests.RegistoUtilizadorRequest;
import com.example.wms.Services.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> registar(@RequestBody RegistoUtilizadorRequest request) {
        try {

            Utilizador novo = service.register(request);
            return ResponseEntity.ok("[SUCCESS] User created with id: " + novo.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
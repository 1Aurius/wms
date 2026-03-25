package com.example.wms.Controllers;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Services.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    @Autowired
    private UtilizadorService service;

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @PostMapping("/createUtilizador")
    public Utilizador createUtilizador(@RequestBody Utilizador utilizador) {
        return service.saveUtilizador(utilizador);
    }

}
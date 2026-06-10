package com.example.wms.Controllers;

import com.example.wms.Models.equipa.Equipa;
import com.example.wms.Models.equipa.EquipaRepository;
import com.example.wms.Models.equipa.EquipaUtilizador;
import com.example.wms.Models.equipa.EquipaUtilizadorId;
import com.example.wms.Models.equipa.EquipaUtilizadorRepository;
import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Models.utilizador.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/equipas")
public class EquipaController {

    @Autowired
    private EquipaRepository equipaRepository;

    @Autowired
    private EquipaUtilizadorRepository equipaUtilizadorRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEquipas() {
        Iterable<Equipa> equipas = equipaRepository.findAll();
        List<Map<String, Object>> responses = new ArrayList<>();
        for (Equipa e : equipas) {
            responses.add(Map.of(
                    "id", e.getId(),
                    "designacao", e.getDesignacao()
            ));
        }
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createEquipa(@RequestBody Map<String, Object> body) {
        String designacao = (String) body.get("designacao");
        if (designacao == null || designacao.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Equipa equipa = new Equipa();
        equipa.setDesignacao(designacao);
        equipa.setDhCriacao(Instant.now());
        // For simplicity we leave criadoPor as null if not provided
        Equipa saved = equipaRepository.save(equipa);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", saved.getId(),
                "designacao", saved.getDesignacao()
        ));
    }

    @GetMapping("/{id}/membros")
    public ResponseEntity<List<Map<String, Object>>> getMembros(@PathVariable Integer id) {
        List<EquipaUtilizador> associacoes = equipaUtilizadorRepository.findByEquipaIdAndDhDesassociacaoIsNull(id);
        List<Map<String, Object>> membros = new ArrayList<>();
        for (EquipaUtilizador eu : associacoes) {
            membros.add(Map.of(
                    "utilizadorId", eu.getUtilizador().getId(),
                    "nome", eu.getUtilizador().getNome(),
                    "dhAssociacao", eu.getDhAssociacao().toString()
            ));
        }
        return ResponseEntity.ok(membros);
    }

    @PostMapping("/{id}/membros")
    public ResponseEntity<?> addMembro(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        Long userId = Long.valueOf(body.get("utilizadorId").toString());
        Equipa equipa = equipaRepository.findById(Long.valueOf(id)).orElse(null);
        Utilizador utilizador = utilizadorRepository.findById(userId).orElse(null);

        if (equipa == null || utilizador == null) {
            return ResponseEntity.notFound().build();
        }

        EquipaUtilizadorId euId = new EquipaUtilizadorId(userId, id);
        EquipaUtilizador eu = equipaUtilizadorRepository.findById(euId).orElse(new EquipaUtilizador());
        eu.setId(euId);
        eu.setEquipa(equipa);
        eu.setUtilizador(utilizador);
        eu.setDhAssociacao(Instant.now());
        eu.setDhDesassociacao(null);

        equipaUtilizadorRepository.save(eu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/membros/{userId}")
    public ResponseEntity<?> removeMembro(@PathVariable Integer id, @PathVariable Long userId) {
        EquipaUtilizadorId euId = new EquipaUtilizadorId(userId, id);
        EquipaUtilizador eu = equipaUtilizadorRepository.findById(euId).orElse(null);
        if (eu != null && eu.getDhDesassociacao() == null) {
            eu.setDhDesassociacao(Instant.now());
            equipaUtilizadorRepository.save(eu);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}


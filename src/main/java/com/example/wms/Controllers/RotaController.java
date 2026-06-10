package com.example.wms.Controllers;

import com.example.wms.Models.rota.Rota;
import com.example.wms.Models.rota.RotaRepository;
import com.example.wms.Responses.RotaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rotas")
public class RotaController {

    @Autowired
    private RotaRepository rotaRepository;

    @GetMapping
    public ResponseEntity<List<RotaResponse>> getAllRotas() {
        Iterable<Rota> rotas = rotaRepository.findAll();
        List<RotaResponse> responses = new ArrayList<>();

        for (Rota r : rotas) {
            Integer veiculoId = (r.getVeiculo() != null) ? r.getVeiculo().getId() : null;
            String veiculoMatricula = (r.getVeiculo() != null) ? r.getVeiculo().getMatricula() : null;
            Integer equipaId = (r.getEquipaTransporte() != null) ? r.getEquipaTransporte().getId() : null;
            String equipaDesignacao = (r.getEquipaTransporte() != null) ? r.getEquipaTransporte().getDesignacao() : null;

            responses.add(new RotaResponse(
                    r.getId(),
                    r.getNome(),
                    r.getEstado(),
                    veiculoId,
                    veiculoMatricula,
                    equipaId,
                    equipaDesignacao
            ));
        }

        return ResponseEntity.ok(responses);
    }

    @Autowired
    private com.example.wms.Models.veiculo.VeiculoRepository veiculoRepository;
    @Autowired
    private com.example.wms.Models.equipa.EquipaRepository equipaRepository;
    @Autowired
    private com.example.wms.Models.utilizador.UtilizadorRepository utilizadorRepository;
    @Autowired
    private com.example.wms.Models.posto.PostoRepository postoRepository;
    @Autowired
    private com.example.wms.Models.paragem.ParagemRepository paragemRepository;

    @org.springframework.web.bind.annotation.PostMapping
    public ResponseEntity<RotaResponse> createRota(@org.springframework.web.bind.annotation.RequestBody com.example.wms.Requests.RotaRequest request) {
        Rota r = new Rota();
        r.setNome(request.nome());
        r.setEstado("CRIADA");
        
        if (request.veiculoId() != null) {
            r.setVeiculo(veiculoRepository.findById(Long.valueOf(request.veiculoId())).orElseThrow());
        }
        if (request.equipaTransporteId() != null) {
            r.setEquipaTransporte(equipaRepository.findById(Long.valueOf(request.equipaTransporteId())).orElseThrow());
        }
        if (request.utilizadorCriadorId() != null) {
            r.setUtilizadorCriador(utilizadorRepository.findById(request.utilizadorCriadorId()).orElse(null));
        }
        
        if (request.dataPrevista() != null && !request.dataPrevista().isBlank()) {
            r.setDataPrevista(java.time.LocalDate.parse(request.dataPrevista()));
        }

        Rota saved = rotaRepository.save(r);

        if (request.postosIds() != null && !request.postosIds().isEmpty()) {
            for (Integer postoId : request.postosIds()) {
                com.example.wms.Models.posto.Posto p = postoRepository.findById(postoId.longValue()).orElse(null);
                if (p != null) {
                    com.example.wms.Models.paragem.Paragem paragem = new com.example.wms.Models.paragem.Paragem();
                    paragem.setRota(saved);
                    paragem.setPosto(p);
                    paragemRepository.save(paragem);
                }
            }
        }
        
        Integer veiculoId = (saved.getVeiculo() != null) ? saved.getVeiculo().getId() : null;
        String veiculoMatricula = (saved.getVeiculo() != null) ? saved.getVeiculo().getMatricula() : null;
        Integer equipaId = (saved.getEquipaTransporte() != null) ? saved.getEquipaTransporte().getId() : null;
        String equipaDesignacao = (saved.getEquipaTransporte() != null) ? saved.getEquipaTransporte().getDesignacao() : null;

        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(new RotaResponse(
                saved.getId(),
                saved.getNome(),
                saved.getEstado(),
                veiculoId,
                veiculoMatricula,
                equipaId,
                equipaDesignacao
        ));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}/status")
    public ResponseEntity<RotaResponse> updateRotaStatus(
            @org.springframework.web.bind.annotation.PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody com.example.wms.Requests.RotaStatusRequest request) {

        Rota r = rotaRepository.findById(Long.valueOf(id)).orElse(null);
        if (r == null) {
            return ResponseEntity.notFound().build();
        }

        if (request.estado() != null && !request.estado().isBlank()) {
            r.setEstado(request.estado());
        }

        if (request.veiculoId() != null) {
            r.setVeiculo(veiculoRepository.findById(Long.valueOf(request.veiculoId())).orElse(null));
        }

        if (request.equipaId() != null) {
            r.setEquipaTransporte(equipaRepository.findById(Long.valueOf(request.equipaId())).orElse(null));
        }

        Rota saved = rotaRepository.save(r);

        Integer veiculoId = (saved.getVeiculo() != null) ? saved.getVeiculo().getId() : null;
        String veiculoMatricula = (saved.getVeiculo() != null) ? saved.getVeiculo().getMatricula() : null;
        Integer equipaId = (saved.getEquipaTransporte() != null) ? saved.getEquipaTransporte().getId() : null;
        String equipaDesignacao = (saved.getEquipaTransporte() != null) ? saved.getEquipaTransporte().getDesignacao() : null;

        return ResponseEntity.ok(new RotaResponse(
                saved.getId(),
                saved.getNome(),
                saved.getEstado(),
                veiculoId,
                veiculoMatricula,
                equipaId,
                equipaDesignacao
        ));
    }

    @org.springframework.web.bind.annotation.GetMapping("/{id}/paragens")
    public ResponseEntity<List<com.example.wms.Responses.ParagemResponse>> getParagens(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        List<com.example.wms.Models.paragem.Paragem> paragens = paragemRepository.findByRotaIdOrderByIdAsc(id);
        List<com.example.wms.Responses.ParagemResponse> responses = new ArrayList<>();
        for (com.example.wms.Models.paragem.Paragem p : paragens) {
            String postoNome = p.getPosto() != null ? p.getPosto().getDesignacao() : null;
            String postoLocal = p.getPosto() != null ? p.getPosto().getLocal() : null;
            Long postoId = p.getPosto() != null ? p.getPosto().getId().longValue() : null;
            responses.add(new com.example.wms.Responses.ParagemResponse(
                    p.getId(),
                    id.longValue(),
                    postoId,
                    postoNome,
                    postoLocal,
                    p.getDh_chegada(),
                    p.getDh_saida()
            ));
        }
        return ResponseEntity.ok(responses);
    }
}

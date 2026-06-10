package com.example.wms.Controllers;

import com.example.wms.Models.veiculo.Veiculo;
import com.example.wms.Models.veiculo.VeiculoRepository;
import com.example.wms.Responses.VeiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;
    
    @Autowired
    private com.example.wms.Models.modelo.ModeloRepository modeloRepository;
    
    @Autowired
    private com.example.wms.Models.posto.PostoRepository postoRepository;
    
    @Autowired
    private com.example.wms.Models.utilizador.UtilizadorRepository utilizadorRepository;

    @GetMapping
    public ResponseEntity<List<VeiculoResponse>> getAllVeiculos() {
        Iterable<Veiculo> veiculos = veiculoRepository.findAll();
        List<VeiculoResponse> responses = new ArrayList<>();

        for (Veiculo v : veiculos) {
            String modeloDesig = (v.getModelo() != null) ? v.getModelo().getDesignacao() : null;
            Integer postoId = (v.getPosto() != null) ? v.getPosto().getId() : null;
            String postoDesig = (v.getPosto() != null) ? v.getPosto().getDesignacao() : null;

            responses.add(new VeiculoResponse(
                    v.getId(),
                    v.getMatricula(),
                    v.getAproxVolume(),
                    v.getAno(),
                    modeloDesig,
                    postoId,
                    postoDesig
            ));
        }

        return ResponseEntity.ok(responses);
    }

    @org.springframework.web.bind.annotation.PostMapping
    public ResponseEntity<VeiculoResponse> create(@org.springframework.web.bind.annotation.RequestBody com.example.wms.Requests.VeiculoRequest request) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMatricula(request.matricula());
        veiculo.setAproxVolume(request.aproxVolume());
        veiculo.setAno(request.ano());
        
        if (request.modeloId() != null) {
            veiculo.setModelo(modeloRepository.findById(request.modeloId()).orElse(null));
        }
        if (request.postoId() != null) {
            veiculo.setPosto(postoRepository.findById(request.postoId()).orElse(null));
        }
        if (request.utilizadorCriadorId() != null) {
            veiculo.setUtilizadorCriador(utilizadorRepository.findById(request.utilizadorCriadorId()).orElse(null));
        }
        
        Veiculo saved = veiculoRepository.save(veiculo);
        
        String modeloDesig = (saved.getModelo() != null) ? saved.getModelo().getDesignacao() : null;
        Integer postoId = (saved.getPosto() != null) ? saved.getPosto().getId() : null;
        String postoDesig = (saved.getPosto() != null) ? saved.getPosto().getDesignacao() : null;
        
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(new VeiculoResponse(
                saved.getId(),
                saved.getMatricula(),
                saved.getAproxVolume(),
                saved.getAno(),
                modeloDesig,
                postoId,
                postoDesig
        ));
    }
}

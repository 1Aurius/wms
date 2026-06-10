package com.example.wms.Services;

import com.example.wms.Models.requesicao.Requesicao;
import com.example.wms.Models.requesicao.RequisicaoRepository;
import com.example.wms.Models.requesicaoStock.RequesicaoStock;
import com.example.wms.Models.requesicaoStock.RequisicaoStockRepository;
import com.example.wms.Models.stock.StockRepository;
import com.example.wms.Models.utilizador.UtilizadorRepository;
import com.example.wms.Requests.RequisicaoStockRequest;
import com.example.wms.Responses.RequesicaoStockHeaderResponse;
import com.example.wms.Responses.RequisicaoStockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class RequisicaoStockService {
    private final RequisicaoRepository requesicaoRepository;
    private final RequisicaoStockRepository requisicaoStockRepository;
    private final StockRepository stockRepository;
    private final UtilizadorRepository utilizadorRepository;

    public RequisicaoStockService(RequisicaoRepository requesicaoRepository,
                                  RequisicaoStockRepository requisicaoStockRepository,
                                  StockRepository stockRepository,
                                  UtilizadorRepository utilizadorRepository) {

        this.requesicaoRepository = requesicaoRepository;
        this.requisicaoStockRepository = requisicaoStockRepository;
        this.stockRepository = stockRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<RequesicaoStockHeaderResponse> getAllHeaders() {
        return requisicaoStockRepository.findAll().stream()
                .map(RequesicaoStockHeaderResponse::from)
                .toList();
    }

    public RequesicaoStockHeaderResponse validate(Integer id, Long validadorId, String status) {
        RequesicaoStock rs = requisicaoStockRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RequesicaoStock not found"));

        if (!status.equals("APROVADO") && !status.equals("REJEITADO")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status");
        }

        rs.setEstado(status);
        rs.setDhValidacao(Instant.now());
        
        if (validadorId != null) {
            rs.setUtilizadorValidador(utilizadorRepository.findById(validadorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilizador validador not found")));
        }

        return RequesicaoStockHeaderResponse.from(requisicaoStockRepository.save(rs));
    }

    public RequesicaoStockHeaderResponse createHeader(String descricao, Long utilizadorCriadorId) {
        RequesicaoStock rs = new RequesicaoStock();
        rs.setDescricao(descricao);
        rs.setEstado("PENDENTE");
        rs.setDhRegisto(Instant.now());
        
        if (utilizadorCriadorId != null) {
            rs.setUtilizadorCriador(utilizadorRepository.findById(utilizadorCriadorId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilizador criador not found")));
        }

        return RequesicaoStockHeaderResponse.from(requisicaoStockRepository.save(rs));
    }

    public List<RequisicaoStockResponse> getAll() {
        return requesicaoRepository.findAll().stream()
                .map(RequisicaoStockResponse::from)
                .toList();
    }

    public RequisicaoStockResponse getById(Long id) {
        return requesicaoRepository.findById(id)
                .map(RequisicaoStockResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<RequisicaoStockResponse> getByRequesicaoStockId(Integer requesicaoStockId) {
        return requesicaoRepository.findAll().stream()
                .filter(r -> r.getRequesicaoStock().getId().equals(requesicaoStockId))
                .map(RequisicaoStockResponse::from)
                .toList();
    }

    public RequisicaoStockResponse create(RequisicaoStockRequest request) {
        Requesicao r = new Requesicao();
        r.setRequesicaoStock(requisicaoStockRepository.findById(Math.toIntExact(request.requisicaoId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RequesicaoStock not found")));
        r.setStock(stockRepository.findById(Math.toIntExact(request.stockId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found")));
        r.setQuant(Math.toIntExact(request.quant()));
        return RequisicaoStockResponse.from(requesicaoRepository.save(r));
    }

    public RequisicaoStockResponse update(Long id, RequisicaoStockRequest request) {
        Requesicao r = requesicaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        r.setRequesicaoStock(requisicaoStockRepository.findById(Math.toIntExact(request.requisicaoId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RequesicaoStock not found")));
        r.setStock(stockRepository.findById(Math.toIntExact(request.stockId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found")));
        r.setQuant(Math.toIntExact(request.quant()));
        return RequisicaoStockResponse.from(requesicaoRepository.save(r));
    }

    public void delete(Long id) {
        if (!requesicaoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        requesicaoRepository.deleteById(id);
    }
}
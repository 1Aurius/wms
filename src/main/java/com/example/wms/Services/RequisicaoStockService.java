package com.example.wms.Services;

import com.example.wms.Models.requesicao.Requesicao;
import com.example.wms.Models.requesicaoStock.RequisicaoStockRepository;
import com.example.wms.Models.stock.StockRepository;
import com.example.wms.Requests.RequisicaoStockRequest;
import com.example.wms.Responses.RequisicaoStockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RequisicaoStockService {
    private final RequisicaoStockRepository requesicaoRepository;
    private final RequisicaoStockRepository requisicaoStockRepository;
    private final StockRepository stockRepository;

    public RequisicaoStockService(RequisicaoStockRepository requesicaoRepository,
                                  RequisicaoStockRepository requisicaoStockRepository,
                                  StockRepository stockRepository) {

        this.requesicaoRepository = requesicaoRepository;
        this.requisicaoStockRepository = requisicaoStockRepository;
        this.stockRepository = stockRepository;
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
        r.setRequesicaoStock(requisicaoStockRepository.findById(request.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RequesicaoStock not found")).getRequesicaoStock());
        r.setStock(stockRepository.findById(Math.toIntExact(request.stockId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found")));
        r.setQuant(Math.toIntExact(request.quant()));
        return RequisicaoStockResponse.from(requesicaoRepository.save(r));
    }

    public RequisicaoStockResponse update(Long id, RequisicaoStockRequest request) {
        Requesicao r = requesicaoRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        r.setRequesicaoStock(requisicaoStockRepository.findById(request.stockId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RequesicaoStock not found")).getRequesicaoStock());
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
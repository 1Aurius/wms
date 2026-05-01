package com.example.wms.Services;

import com.example.wms.Models.posto.Posto;
import com.example.wms.Models.posto.PostoRepository;
import com.example.wms.Requests.PostoRequest;
import com.example.wms.Responses.PostoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostoService {
    private final PostoRepository postoRepository;

    public PostoService(PostoRepository postoRepository) {
        this.postoRepository = postoRepository;
    }

    public List<PostoResponse> getAll() {
        return postoRepository.findAll().stream()
                .map(PostoResponse::from)
                .toList();
    }

    public PostoResponse getById(Long id) {
        return postoRepository.findById(id)
                .map(PostoResponse::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PostoResponse create(PostoRequest request) {
        Posto posto = new Posto();
        posto.setDesignacao(request.designacao());
        posto.setLocal(request.local());
        posto.setCp(request.cp());
        posto.setTipo(request.tipo());
        return PostoResponse.from(postoRepository.save(posto));
    }

    public PostoResponse update(Long id, PostoRequest request) {
        Posto posto = postoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        posto.setDesignacao(request.designacao());
        posto.setLocal(request.local());
        posto.setCp(request.cp());
        posto.setTipo(request.tipo());
        return PostoResponse.from(postoRepository.save(posto));
    }

    public void delete(Long id) {
        if (!postoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        postoRepository.deleteById(id);
    }
}
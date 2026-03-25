package com.example.wms.Services;

import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Models.utilizador.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository repository;

    public Utilizador saveUtilizador(Utilizador utilizador) {
        return repository.save(utilizador);
    }
}
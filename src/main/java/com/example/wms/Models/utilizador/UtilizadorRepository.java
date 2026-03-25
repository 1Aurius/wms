package com.example.wms.Models.utilizador;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilizadorRepository extends CrudRepository<Utilizador, Long> {
    Optional<Utilizador> findByUsername(String username);
}

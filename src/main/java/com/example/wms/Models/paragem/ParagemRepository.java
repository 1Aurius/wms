package com.example.wms.Models.paragem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParagemRepository extends JpaRepository<Paragem, Long> {
    List<Paragem> findByRotaIdOrderByIdAsc(Integer rotaId);
}

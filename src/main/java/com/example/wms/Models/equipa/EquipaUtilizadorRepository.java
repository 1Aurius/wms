package com.example.wms.Models.equipa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipaUtilizadorRepository extends JpaRepository<EquipaUtilizador, EquipaUtilizadorId> {
    List<EquipaUtilizador> findByEquipaIdAndDhDesassociacaoIsNull(Integer equipaId);
}

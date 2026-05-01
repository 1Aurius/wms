package com.example.wms.Models.requesicaoStock;

import com.example.wms.Models.requesicao.Requesicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoStockRepository extends JpaRepository<Requesicao, Long> {}

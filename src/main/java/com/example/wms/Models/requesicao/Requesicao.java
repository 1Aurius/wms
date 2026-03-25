package com.example.wms.Models.requesicao;

import com.example.wms.Models.requesicaoStock.RequesicaoStock;
import com.example.wms.Models.stock.Stock;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "requesicao")
public class Requesicao {
    @Id
    @ColumnDefault("nextval('requesicao_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requesicao_stock_id", nullable = false)
    private RequesicaoStock requesicaoStock;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;

    @Column(name = "quant", nullable = false)
    private Integer quant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RequesicaoStock getRequesicaoStock() {
        return requesicaoStock;
    }

    public void setRequesicaoStock(RequesicaoStock requesicaoStock) {
        this.requesicaoStock = requesicaoStock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

}
package com.example.wms.Models.atributo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "atributo")
public class Atributo {
    @Id
    @ColumnDefault("nextval('atributo_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "designacao", nullable = false)
    private String designacao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

}
package com.example.wms.Models.atributoVeiculo;

import com.example.wms.Models.atributo.Atributo;
import com.example.wms.Models.veiculo.Veiculo;
import jakarta.persistence.*;

@IdClass(AtributoVeiculoId.class)
@Entity
@Table(name = "atributo_veiculo")

public class AtributoVeiculo {
    @Id
    @Column(name = "veiculo_id", nullable = false)
    private Integer veiculoId;

    @Id
    @Column(name = "atributo_id", nullable = false)
    private Integer atributoId;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atributo_id", nullable = false)
    private Atributo atributo;

    public Integer getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Integer veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Integer getAtributoId() {
        return atributoId;
    }

    public void setAtributoId(Integer atributoId) {
        this.atributoId = atributoId;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Atributo getAtributo() {
        return atributo;
    }

    public void setAtributo(Atributo atributo) {
        this.atributo = atributo;
    }

}
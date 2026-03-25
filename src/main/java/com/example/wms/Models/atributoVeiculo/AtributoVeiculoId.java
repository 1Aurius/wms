package com.example.wms.Models.atributoVeiculo;

import java.io.Serializable;
import java.util.Objects;

public class AtributoVeiculoId implements Serializable {
    public Integer veiculoId;

    public Integer atributoId;

    public AtributoVeiculoId() {
    }

    public AtributoVeiculoId(Integer veiculoId, Integer atributoId) {
        this.veiculoId = veiculoId;
        this.atributoId = atributoId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtributoVeiculoId entity = (AtributoVeiculoId) o;
        return Objects.equals(this.veiculoId, entity.veiculoId) &&
                Objects.equals(this.atributoId, entity.atributoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(veiculoId, atributoId);
    }
}
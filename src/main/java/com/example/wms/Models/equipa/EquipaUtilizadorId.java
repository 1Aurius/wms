package com.example.wms.Models.equipa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EquipaUtilizadorId implements Serializable {

    @Column(name = "utilizador_id")
    private Long utilizadorId;

    @Column(name = "equipa_id")
    private Integer equipaId;

    public EquipaUtilizadorId() {}

    public EquipaUtilizadorId(Long utilizadorId, Integer equipaId) {
        this.utilizadorId = utilizadorId;
        this.equipaId = equipaId;
    }

    public Long getUtilizadorId() {
        return utilizadorId;
    }
    public void setUtilizadorId(Long utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public Integer getEquipaId() {
        return equipaId;
    }
    public void setEquipaId(Integer equipaId) {
        this.equipaId = equipaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipaUtilizadorId that = (EquipaUtilizadorId) o;
        return Objects.equals(utilizadorId, that.utilizadorId) &&
               Objects.equals(equipaId, that.equipaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(utilizadorId, equipaId);
    }
}

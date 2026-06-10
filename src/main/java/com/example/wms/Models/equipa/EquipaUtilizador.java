package com.example.wms.Models.equipa;

import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "equipa_utilizador")
public class EquipaUtilizador {

    @EmbeddedId
    private EquipaUtilizadorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("utilizadorId")
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("equipaId")
    @JoinColumn(name = "equipa_id")
    private Equipa equipa;

    @Column(name = "dh_associacao")
    private Instant dhAssociacao;

    @Column(name = "dh_desassociacao")
    private Instant dhDesassociacao;

    public EquipaUtilizadorId getId() {
        return id;
    }

    public void setId(EquipaUtilizadorId id) {
        this.id = id;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Equipa getEquipa() {
        return equipa;
    }

    public void setEquipa(Equipa equipa) {
        this.equipa = equipa;
    }

    public Instant getDhAssociacao() {
        return dhAssociacao;
    }

    public void setDhAssociacao(Instant dhAssociacao) {
        this.dhAssociacao = dhAssociacao;
    }

    public Instant getDhDesassociacao() {
        return dhDesassociacao;
    }

    public void setDhDesassociacao(Instant dhDesassociacao) {
        this.dhDesassociacao = dhDesassociacao;
    }
}

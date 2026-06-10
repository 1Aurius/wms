package com.example.wms.Models.paragem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Paragem {

    @Id
    @GeneratedValue
    private Long id;
    private Date dh_chegada;
    private Date dh_saida;

    public Paragem(Date dh_chegada, Date dh_saida) {
        this.dh_chegada = dh_chegada;
        this.dh_saida = dh_saida;
    }

    public Paragem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDh_chegada() {
        return dh_chegada;
    }

    public void setDh_chegada(Date dh_chegada) {
        this.dh_chegada = dh_chegada;
    }

    public Date getDh_saida() {
        return dh_saida;
    }

    public void setDh_saida(Date dh_saida) {
        this.dh_saida = dh_saida;
    }

    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "rota_id")
    private com.example.wms.Models.rota.Rota rota;

    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "posto_id")
    private com.example.wms.Models.posto.Posto posto;

    public com.example.wms.Models.rota.Rota getRota() {
        return rota;
    }

    public void setRota(com.example.wms.Models.rota.Rota rota) {
        this.rota = rota;
    }

    public com.example.wms.Models.posto.Posto getPosto() {
        return posto;
    }

    public void setPosto(com.example.wms.Models.posto.Posto posto) {
        this.posto = posto;
    }
}

package com.example.wms.Models.requesicaoStock;

import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "requesicao_stock")
public class RequesicaoStock {
    @Id
    @ColumnDefault("nextval('requesicao_stock_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "dh_registo")
    private Instant dhRegisto;

    @Column(name = "dh_validacao")
    private Instant dhValidacao;

    @Column(name = "descricao", length = Integer.MAX_VALUE)
    private String descricao;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_criador_id")
    private Utilizador utilizadorCriador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_validador_id")
    private Utilizador utilizadorValidador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getDhRegisto() {
        return dhRegisto;
    }

    public void setDhRegisto(Instant dhRegisto) {
        this.dhRegisto = dhRegisto;
    }

    public Instant getDhValidacao() {
        return dhValidacao;
    }

    public void setDhValidacao(Instant dhValidacao) {
        this.dhValidacao = dhValidacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Utilizador getUtilizadorCriador() {
        return utilizadorCriador;
    }

    public void setUtilizadorCriador(Utilizador utilizadorCriador) {
        this.utilizadorCriador = utilizadorCriador;
    }

    public Utilizador getUtilizadorValidador() {
        return utilizadorValidador;
    }

    public void setUtilizadorValidador(Utilizador utilizadorValidador) {
        this.utilizadorValidador = utilizadorValidador;
    }

}
package com.example.wms.Models.veiculo;

import com.example.wms.Models.modelo.Modelo;
import com.example.wms.Models.posto.Posto;
import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "veiculo")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('veiculo_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "matricula", nullable = false, length = 20)
    private String matricula;

    @Column(name = "aprox_volume")
    private Double aproxVolume;

    @Column(name = "ano", length = 4)
    private String ano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_criador_id")
    private Utilizador utilizadorCriador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "posto_id", nullable = false)
    private Posto posto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getAproxVolume() {
        return aproxVolume;
    }

    public void setAproxVolume(Double aproxVolume) {
        this.aproxVolume = aproxVolume;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Utilizador getUtilizadorCriador() {
        return utilizadorCriador;
    }

    public void setUtilizadorCriador(Utilizador utilizadorCriador) {
        this.utilizadorCriador = utilizadorCriador;
    }

    public Posto getPosto() {
        return posto;
    }

    public void setPosto(Posto posto) {
        this.posto = posto;
    }

}
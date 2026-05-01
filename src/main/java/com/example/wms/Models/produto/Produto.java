package com.example.wms.Models.produto;

import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "serial_code", length = 100)
    private String serialCode;

    @Column(name = "nome")
    private String nome;

    @Column(name = "unidade", length = 20)
    private String unidade;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "aprox_volume")
    private Double aproxVolume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_criador_id")
    private Utilizador utilizadorCriador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getAproxVolume() {
        return aproxVolume;
    }

    public void setAproxVolume(Double aproxVolume) {
        this.aproxVolume = aproxVolume;
    }

    public Utilizador getUtilizadorCriador() {
        return utilizadorCriador;
    }

    public void setUtilizadorCriador(Utilizador utilizadorCriador) {
        this.utilizadorCriador = utilizadorCriador;
    }

}
package com.example.wms.Models.rota;

import com.example.wms.Models.equipa.Equipa;
import com.example.wms.Models.utilizador.Utilizador;
import com.example.wms.Models.veiculo.Veiculo;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "rota")
public class Rota {
    @Id
    @ColumnDefault("nextval('rota_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilizador_criador_id")
    private Utilizador utilizadorCriador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipa_transporte_id", nullable = false)
    private Equipa equipaTransporte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Equipa getEquipaTransporte() {
        return equipaTransporte;
    }

    public void setEquipaTransporte(Equipa equipaTransporte) {
        this.equipaTransporte = equipaTransporte;
    }

}
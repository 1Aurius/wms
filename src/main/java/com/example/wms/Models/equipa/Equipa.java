package com.example.wms.Models.equipa;

import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "equipa")
public class Equipa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('equipa_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "dh_criacao")
    private Instant dhCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_id")
    private Utilizador criadoPor;

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

    public Instant getDhCriacao() {
        return dhCriacao;
    }

    public void setDhCriacao(Instant dhCriacao) {
        this.dhCriacao = dhCriacao;
    }

    public Utilizador getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Utilizador criadoPor) {
        this.criadoPor = criadoPor;
    }

}
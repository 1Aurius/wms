package com.example.wms.Models.posto;

import com.example.wms.Models.utilizador.Utilizador;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "posto")
public class Posto {
    @Id
    @ColumnDefault("nextval('posto_id_seq')")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "designacao")
    private String designacao;

    @Column(name = "local")
    private String local;

    @Column(name = "cp", length = 12)
    private String cp;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gerente_posto_id")
    private Utilizador gerentePosto;

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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Utilizador getGerentePosto() {
        return gerentePosto;
    }

    public void setGerentePosto(Utilizador gerentePosto) {
        this.gerentePosto = gerentePosto;
    }

}
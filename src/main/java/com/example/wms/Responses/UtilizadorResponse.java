package com.example.wms.Responses;

import com.example.wms.Models.utilizador.Utilizador;

import java.util.Date;

public class UtilizadorResponse {
    private Long id;
    private String username;
    private Date dn;
    private boolean isAdmin;
    private boolean isGestorRotas;
    private boolean isGestor;
    private boolean isLoja;
    private boolean isArmazem;

    public UtilizadorResponse(Long id, String username, Date dn, boolean isAdmin, boolean isGestorRotas, boolean isGestor, boolean isLoja, boolean isArmazem) {
        this.id = id;
        this.username = username;
        this.dn = dn;
        this.isAdmin = isAdmin;
        this.isGestorRotas = isGestorRotas;
        this.isGestor = isGestor;
        this.isLoja = isLoja;
        this.isArmazem = isArmazem;
    }

    public static UtilizadorResponse from(Utilizador utilizador) {
        return new UtilizadorResponse(
                utilizador.getId(),
                utilizador.getUsername(),
                utilizador.getDn(),
                utilizador.isAdmin(),
                utilizador.isGestorRotas(),
                utilizador.isGestor(),
                utilizador.isLoja(),
                utilizador.isArmazem()
        );
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getDn() {
        return dn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isGestorRotas() {
        return isGestorRotas;
    }

    public boolean isGestor() {
        return isGestor;
    }

    public boolean isLoja() {
        return isLoja;
    }

    public boolean isArmazem() {
        return isArmazem;
    }
}

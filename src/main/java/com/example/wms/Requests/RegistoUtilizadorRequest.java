package com.example.wms.Requests;

import java.util.Date;

public class RegistoUtilizadorRequest {
    private String nome;
    private String password;
    private Date dn;
    private boolean isAdmin;
    private boolean isGestorRotas;
    private boolean isGestor;
    private boolean isLoja;
    private boolean isArmazem;

    // Getters & Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Date getDn() { return dn; }
    public void setDn(Date dn) { this.dn = dn; }
    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }
    public boolean isGestorRotas() { return isGestorRotas; }
    public void setGestorRotas(boolean gestorRotas) { isGestorRotas = gestorRotas; }
    public boolean isGestor() { return isGestor; }
    public void setGestor(boolean gestor) { isGestor = gestor; }
    public boolean isLoja() { return isLoja; }
    public void setLoja(boolean loja) { isLoja = loja; }
    public boolean isArmazem() { return isArmazem; }
    public void setArmazem(boolean armazem) { isArmazem = armazem; }
}
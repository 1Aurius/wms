package com.example.wms.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class RegistoUtilizadorRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String nome;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 8 characters")
    private String password;

    @NotNull(message = "Date of birth is required")
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
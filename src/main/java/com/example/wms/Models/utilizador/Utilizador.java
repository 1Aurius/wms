package com.example.wms.Models.utilizador;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Utilizador implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String password;
    private Date dn;
    private boolean isAdmin;
    private boolean isGestorRotas;
    private boolean isGestor;
    private boolean isLoja;
    private boolean isArmazem;

    public Utilizador( String nome, String password, Date dn, boolean isAdmin, boolean isGestorRotas, boolean isGestor, boolean isLoja,boolean isArmazem) {
        this.nome = nome;
        this.password = password;
        this.dn = dn;
        this.isAdmin = isAdmin;
        this.isGestorRotas = isGestorRotas;
        this.isGestor = isGestor;
        this.isLoja = isLoja;
        this.isArmazem = isArmazem;

    }

    public Utilizador() {

    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (isAdmin) authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (isGestorRotas) authorities.add(new SimpleGrantedAuthority("ROLE_GESTOR_ROTAS"));
        if (isGestor) authorities.add(new SimpleGrantedAuthority("ROLE_GESTOR"));
        if (isLoja) authorities.add(new SimpleGrantedAuthority("ROLE_LOJA"));
        if (isArmazem) authorities.add(new SimpleGrantedAuthority("ROLE_ARMAZEM"));
        return authorities;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return nome;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDn() {
        return dn;
    }
    public void setDn(Date dn) {
        this.dn = dn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isGestorRotas() {
        return isGestorRotas;
    }
    public void setGestorRotas(boolean gestorRotas) {
        isGestorRotas = gestorRotas;
    }

    public boolean isGestor() {
        return isGestor;
    }
    public void setGestor(boolean gestor) {
        isGestor = gestor;
    }

    public boolean isLoja() {
        return isLoja;
    }
    public void setLoja(boolean loja) {
        isLoja = loja;
    }

}

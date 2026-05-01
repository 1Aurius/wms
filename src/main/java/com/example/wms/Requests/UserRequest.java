package com.example.wms.Requests;

public record UserRequest(
        String nome,
        String password,
        String dn,
        Boolean isAdmin,
        Boolean isGestorRotas,
        Boolean isGestor,
        Boolean isLoja,
        Boolean isArmazem
) {}
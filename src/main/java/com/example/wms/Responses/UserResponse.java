package com.example.wms.Responses;

import com.example.wms.Models.utilizador.Utilizador;

import java.util.Date;

public record UserResponse(
        Long id,
        String nome,
        Date dn,
        Boolean isAdmin,
        Boolean isGestorRotas,
        Boolean isGestor,
        Boolean isLoja,
        Boolean isArmazem
) {
    public static UserResponse from(Utilizador user) {
        return new UserResponse(
                user.getId(),
                user.getNome(),
                user.getDn(),
                user.isAdmin(),
                user.isGestorRotas(),
                user.isGestor(),
                user.isLoja(),
                user.isArmazem()
        );
    }
}
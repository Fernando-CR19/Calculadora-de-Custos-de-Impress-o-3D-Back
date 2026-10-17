package com.Geek3DShop.Calculadora_3D.material.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateMaterialDto(@NotBlank(message = "O nome do material é obrigatório") String nome) {
    public UpdateMaterialDto {
        if (nome != null) {
            nome = nome.strip();
        }
    }
}

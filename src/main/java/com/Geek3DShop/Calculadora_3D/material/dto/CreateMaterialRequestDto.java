package com.Geek3DShop.Calculadora_3D.material.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateMaterialRequestDto(@NotBlank(message = "O nome do material é obrigatório") String nome) {
    public CreateMaterialRequestDto {
        if (nome != null) {
            nome = nome.strip();
        }
    }
}

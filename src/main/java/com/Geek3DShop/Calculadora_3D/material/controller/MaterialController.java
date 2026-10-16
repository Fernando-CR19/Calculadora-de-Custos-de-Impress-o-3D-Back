package com.Geek3DShop.Calculadora_3D.material.controller;

import com.Geek3DShop.Calculadora_3D.material.dto.CreateMaterialRequestDto;
import com.Geek3DShop.Calculadora_3D.material.dto.MaterialResponseDto;
import com.Geek3DShop.Calculadora_3D.material.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiais")
public class MaterialController {
    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponseDto createMaterial(@RequestBody CreateMaterialRequestDto materialRequestDto) {
        return materialService.createMaterial(materialRequestDto);
    }

    @GetMapping()
    public List<MaterialResponseDto> listMaterials() {
        return materialService.listMaterials();
    }
}

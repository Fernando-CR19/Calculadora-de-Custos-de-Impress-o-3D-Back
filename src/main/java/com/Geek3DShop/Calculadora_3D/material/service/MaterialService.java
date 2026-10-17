package com.Geek3DShop.Calculadora_3D.material.service;

import com.Geek3DShop.Calculadora_3D.material.dto.CreateMaterialRequestDto;
import com.Geek3DShop.Calculadora_3D.material.dto.MaterialResponseDto;
import com.Geek3DShop.Calculadora_3D.material.dto.UpdateMaterialDto;
import com.Geek3DShop.Calculadora_3D.material.entity.Material;
import com.Geek3DShop.Calculadora_3D.material.repository.MaterialRepository;
import com.Geek3DShop.Calculadora_3D.shared.exception.FeatureAlreadyExistsException;
import com.Geek3DShop.Calculadora_3D.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Transactional
    public MaterialResponseDto createMaterial(CreateMaterialRequestDto materialDto) {
        boolean alreadyExists = materialRepository.existsByNomeIgnoreCase(materialDto.nome());

        if (alreadyExists) {
            throw new FeatureAlreadyExistsException("Material: " + materialDto.nome() + " já está cadastrado");
        }

        var entity = new Material(materialDto.nome());
        var entitySaved = materialRepository.save(entity);
        return new MaterialResponseDto(entitySaved.getUuid(), entitySaved.getNome());
    }

    public List<MaterialResponseDto> listMaterials() {
        var list = materialRepository.findByActiveTrueAndDeletedFalseOrderByNomeAsc();
        return list.stream().map(material -> new MaterialResponseDto(material.getUuid(), material.getNome())).toList();
    }

    @Transactional
    public MaterialResponseDto updateMaterial(UUID uuid, UpdateMaterialDto materialDto) {
        Optional<Material> material = materialRepository.findByUuidAndActiveTrueAndDeletedFalse(uuid);

        if (material.isEmpty()) {
            throw new ResourceNotFoundException("Material: " + uuid + " não encontrado");
        }

        Optional<Material> entityAlreadySaved = materialRepository.findByNomeIgnoreCaseAndUuidNot(materialDto.nome(), uuid);

        if (entityAlreadySaved.isPresent()) {
            throw new FeatureAlreadyExistsException("Material: " + materialDto.nome() + " já está cadastrado");
        }

        Material entity = material.get();
        entity.setNome(materialDto.nome());

        materialRepository.save(entity);

        return new MaterialResponseDto(entity.getUuid(), entity.getNome());
    }
}

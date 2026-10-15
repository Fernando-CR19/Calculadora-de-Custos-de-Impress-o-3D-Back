package com.Geek3DShop.Calculadora_3D.material.repository;

import com.Geek3DShop.Calculadora_3D.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MaterialRepository extends JpaRepository<Material, Integer> {

    Optional<Material> findByUuid(UUID uuid);

}

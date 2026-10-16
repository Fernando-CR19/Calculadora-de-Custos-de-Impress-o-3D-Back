package com.Geek3DShop.Calculadora_3D.material.repository;

import com.Geek3DShop.Calculadora_3D.material.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    Optional<Material> findByUuid(UUID uuid);

    boolean existsByNomeIgnoreCase(String nome);
}

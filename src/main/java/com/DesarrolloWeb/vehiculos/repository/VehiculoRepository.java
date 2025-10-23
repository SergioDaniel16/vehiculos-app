package com.DesarrolloWeb.vehiculos.repository;

import com.DesarrolloWeb.vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    // JpaRepository ya proporciona métodos como:
    // - save() : para insertar y actualizar
    // - findById() : para buscar por placa
    // - findAll() : para listar todos
    // - existsById() : para verificar existencia
}
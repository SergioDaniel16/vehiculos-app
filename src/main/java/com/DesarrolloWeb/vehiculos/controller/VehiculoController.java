package com.DesarrolloWeb.vehiculos.controller;

import com.DesarrolloWeb.vehiculos.model.Vehiculo;
import com.DesarrolloWeb.vehiculos.repository.VehiculoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregarVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (vehiculoRepository.existsById(vehiculo.getPlaca())) {
                response.put("success", false);
                response.put("mensaje", "Error: Ya existe un vehículo con la placa " + vehiculo.getPlaca());
                response.put("vehiculos", vehiculoRepository.findAll());
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            
            Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            
            response.put("success", true);
            response.put("mensaje", "Vehículo agregado exitosamente con placa: " + vehiculoGuardado.getPlaca());
            response.put("vehiculo", vehiculoGuardado);
            response.put("vehiculos", vehiculos);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", "Error al agregar el vehículo: " + e.getMessage());
            response.put("vehiculos", vehiculoRepository.findAll());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Map<String, Object>> actualizarVehiculo(
            @PathVariable String placa,
            @Valid @RequestBody Vehiculo vehiculo) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Optional<Vehiculo> vehiculoExistente = vehiculoRepository.findById(placa);
            
            if (vehiculoExistente.isEmpty()) {
                response.put("success", false);
                response.put("mensaje", "Error: No existe un vehículo con la placa " + placa);
                response.put("vehiculos", vehiculoRepository.findAll());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            vehiculo.setPlaca(placa);
            Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            
            response.put("success", true);
            response.put("mensaje", "Vehículo actualizado exitosamente con placa: " + placa);
            response.put("vehiculo", vehiculoActualizado);
            response.put("vehiculos", vehiculos);
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("mensaje", "Error al actualizar el vehículo: " + e.getMessage());
            response.put("vehiculos", vehiculoRepository.findAll());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
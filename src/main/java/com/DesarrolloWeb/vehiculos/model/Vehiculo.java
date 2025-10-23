package com.DesarrolloWeb.vehiculos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @NotBlank(message = "La placa no puede estar vacía")
    private String placa;

    @NotBlank(message = "El modelo no puede estar vacío")
    private String modelo;

    @NotBlank(message = "El color no puede estar vacío")
    private String color;

    @NotNull(message = "El número de puertas no puede ser nulo")
    @Min(value = 1, message = "El vehículo debe tener al menos 1 puerta")
    private Integer puertas;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String modelo, String color, Integer puertas) {
        this.placa = placa;
        this.modelo = modelo;
        this.color = color;
        this.puertas = puertas;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", puertas=" + puertas +
                '}';
    }
}
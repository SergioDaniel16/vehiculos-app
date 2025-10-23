package com.DesarrolloWeb.vehiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehiculosApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiculosApplication.class, args);
        System.out.println("\n===========================================");
        System.out.println("Aplicación de Vehículos Iniciada");
        System.out.println("URL: http://localhost:8080");
        System.out.println("===========================================\n");
    }
}
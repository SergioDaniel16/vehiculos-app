# Gestión de Vehículos

Aplicación web para administrar vehículos usando Spring Boot y SQL Server.

## Requisitos

- Java 21
- Maven 3.9+
- SQL Server
- Navegador web

## Configuración

1. Crear la base de datos en SQL Server:
```sql
CREATE DATABASE VehiculosDB;
```

2. Copiar `application.properties.example` a `application.properties` y configurar:
```properties
spring.datasource.url=jdbc:sqlserver://TU_SERVIDOR:1433;databaseName=VehiculosDB;...
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

## Ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

Abrir http://localhost:8080

## API REST

**POST** `/api/vehiculos` - Agregar vehículo  
**PUT** `/api/vehiculos/{placa}` - Actualizar vehículo

Ambos métodos retornan la lista actualizada de vehículos.

## Estructura

- Backend: Spring Boot + JPA
- Frontend: HTML, CSS, JavaScript
- Base de datos: SQL Server

Proyecto para la clase de Desarrollo Web.
# Proyecto Microservicios con Spring Boot

Este repositorio contiene **dos microservicios desarrollados con Spring Boot** como parte de la prueba técnica:

- **Microservicio Persona-Cliente** → Maneja información de personas/clientes.  
- **Microservicio Cuenta-Movimientos** → Maneja cuentas bancarias y movimientos.

---


## Cómo levantar los microservicios

### Por medio de docker

Los dos microservicios cuentas con docker-compose para la base de datos y Dockerfile para el proyecto

**Levantar la base de datos**

```bash
docker-compose up -d
```

**Levantar el proyecto mediante docker**
```bash
docker build -t mi-springboot-app .
docker run -p 8080:8080 mi-springboot-app
```

**Nota:** Se ha logrado realizar los microservicios funcionales conforme a las especificaciones, lo que ha falatado es la conexión de los mismos por falta de tiempo, se agradece la compresión.

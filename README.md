# 23/06/2026
# 🐾 PatitasMed

PatitasMed es una aplicación web ideada con el propósito de facilitar la gestión y el seguimiento de la información relacionada con la salud y el bienestar de las mascotas (en inicio solamente para perros y gatos). Este sistema permite que los usuarios creen una cuenta personal mediante la cual gestionen la salud de cada una de sus mascotas y sus respectivas citas médicas veterinarias. 


## Funcionalidades implementadas

### 👤 Gestión de usuarios
- Registro de usuarios.
- Inicio de sesión.
- Perfil de usuario (En desarrollo).
- Eliminar usuario (En desarrollo).

### 🐾 Gestión de mascotas
- Registrar mascotas.
- Consultar mascotas asociadas al usuario.
- Consultar citas veterinarias asociadas a la mascota.
- Modificar información de mascotas.
- Eliminar mascotas.

### 📅 Gestión de Citas médicas veterinarias
- Agendar citas veterinarias.
- Seleccionar clínica veterinaria.
- Seleccionar veterinario según la clínica.
- Asociar citas a una mascota.
- Modificar citas.
- Cancelar citas.

### 📋 Historia médica(Aún en desarrollo)
- Visualización de la historia clínica de cada mascota.

## Tecnologías utilizadas

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate

### Frontend
- Vue.js
- Axios
- HTML5
- CSS3
- JavaScript

### Base de datos
-MySQL Workbench

### Herramientas
- Eclipse IDE
- Visual Studio Code
- MySQL Workbench
- Git
- GitHub
- Postman

---

## Estructura del proyecto

```
PatitasMed
│
├── backend
│
├── frontend
│
└── database
```

---

## Configuración de la Base de Datos

1. Crear una base de datos llamada:

```sql
CREATE DATABASE patitasmed;
```

## Ejecución del Backend

1. Abrir la carpeta PatitasMed Backend en Spring Boot.
   App PatitasMed\PatitasMed Backend
   
2. Esperar la descarga de dependencias Maven.
3. Configurar las credenciales para la base de datos correctamente en Spring Boot:

```properties
application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/patitasmed
spring.datasource.username=root
spring.datasource.password=123456
```

---
4. Ejecutar la clase principal:

```java
PatitasMedApplication.java
```

4. Validar con las rutas y metodos que se encuentran en los controller haciendo uso de Postman, teniendo en cuenta que el puerto principal es el siguiente:

```
http://localhost:8080
```

---

5. 

## Ejecución del Frontend

1. Abrir la carpeta PatitasMed Frontend.
   App PatitasMed\PatitasMed Frontend\frontpatitasmed
   
2. Instalar dependencias:

```bash
npm install
```

3. Ejecutar el proyecto:

```bash
npm run serve
```

4. Abrir:

```
http://localhost:8081
```

(o el puerto que asigne Vue).

---

## Desarrollador

**Yubeli Carabalí**

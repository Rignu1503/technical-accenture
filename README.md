# Technical Accenture

API REST reactiva para la gestión de franquicias, sucursales y productos, desarrollada con **Java 21**, **Spring Boot WebFlux** y **PostgreSQL** mediante **R2DBC**.

---

## Tecnologías

* **Java 21**
* **Spring Boot / Spring WebFlux** 
* **Spring Data R2DBC**
* **PostgreSQL 16**
* **MapStruct**
* **Lombok**
* **Jakarta Validation**
* **Gradle**
* **Docker y Docker Compose**

---

## 📋 Requisitos

* **Docker**.
* **Java 21** *(únicamente si deseas compilar o ejecutar la aplicación sin Docker)*.

---

## 🛠️ Instalación y ejecución

El proyecto está configurado con un `Dockerfile` multi-stage que compila el código fuente y genera el paquete `.jar` automáticamente dentro del contenedor, por lo que **no es necesario compilar manualmente ni tener Java o Gradle instalados**.

### 1. Clonar el repositorio
```bash
git clone https://github.com/Rignu1503/technical-accenture.git
cd technical-accenture
```

### 2. Ejecutar con Docker Compose
```bash
docker compose up --build -d
```

La API y la base de datos se inicializarán automáticamente. La API estará disponible en:  
👉 **`http://localhost:8080/api/v1`**

### 3. Para detener los servicios
```bash
docker compose down
```

---

## 📖 Endpoints principales

### Franquicias (`/franchises`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/franchises` | Listar franquicias |
| `POST` | `/franchises` | Crear franquicia |
| `PUT` | `/franchises/{id}` | Actualizar franquicia |

---

### Sucursales (`/branches`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/branches` | Listar sucursales |
| `GET` | `/branches/franchise/{franchiseId}` | Listar sucursales por franquicia |
| `POST` | `/branches` | Crear sucursal |
| `PUT` | `/branches/{id}` | Actualizar sucursal |

---

### Productos (`/products`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| `GET` | `/products` | Listar productos |
| `POST` | `/products` | Crear producto |
| `PUT` | `/products/{id}/name` | Actualizar nombre |
| `PUT` | `/products/{id}/stock` | Actualizar stock |
| `DELETE` | `/products/{id}` | Eliminar producto |
| `GET` | `/products/top-stock/franchise/{id}` | Obtener producto con mayor stock por sucursal de una franquicia |

---

## Manejo de errores

La aplicación cuenta con un manejador global de excepciones mediante `@RestControllerAdvice` (`BadRequestController`), encargado de centralizar y estandarizar las respuestas para:
* Errores de validación de campos (`WebExchangeBindException`).
* Solicitudes incorrectas y reglas de negocio (`BadRequestException`).
* IDs no encontrados.

---

## ⚙️ Base de datos

**PostgreSQL 16** se ejecuta automáticamente mediante Docker Compose y utiliza un volumen persistente (`postgres_data`) para conservar la información.

La configuración de conexión se realiza mediante variables de entorno:
* `DB_URL`
* `DB_USERNAME`
* `DB_PASSWORD`

El esquema de base de datos (`schema.sql`) se inicializa automáticamente en el primer arranque de la aplicación.

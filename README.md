# 🎓 API de gestión académica — Proyecto integrador Backend II

API REST para administrar la información académica de una institución educativa: usuarios y roles, estudiantes, docentes, programas, materias, grupos, periodos, matrículas y notas.

> Proyecto integrador realizado en equipo de 3 personas en **CESDE**, en el módulo Backend II del programa de Desarrollo de Software.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)

---

## ✨ Qué hace

- **13 entidades** relacionadas que modelan la vida académica: usuarios, roles, estudiantes, docentes, programas, materias, grupos, periodos, matrículas y calificaciones.
- **65 endpoints REST** organizados en 12 controladores.
- Búsquedas útiles además del CRUD: estudiante por número de documento, usuario por correo, rol por nombre, matrículas y notas de un estudiante y materias de un grupo.
- **DTOs** de entrada y salida con validaciones, así la API no expone las entidades ni datos como la contraseña.
- **Manejo global de errores** con excepciones propias y respuestas con el código y el mensaje del error.
- Documentación con **Swagger**.

## 🧰 Tecnologías

| Área | Herramientas |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.2 (Spring Web, Spring Data JPA, Validation) |
| Base de datos | MySQL |
| Documentación | Swagger (springdoc-openapi) |
| Construcción | Maven |

## 🏗️ Estructura

```
src/main/java/co/edu/cesde/ga/aplicacion
├── application
│   └── service           → Interfaces de los servicios
│       └── Impl          → Lógica y reglas de negocio
├── controller            → Controladores REST
│   └── dto               → DTOs de entrada (Create / Update)
│       └── response      → DTOs de salida y ErrorResponse
├── domain                → Entidades JPA y EnrollmentStatus
│   ├── exceptions        → Excepciones del negocio
│   └── repository        → Repositorios JPA
├── presentation
│   └── advice            → Manejador global de errores
└── CorsConfig            → Configuración de CORS
```

## 📡 Endpoints

URL base: `http://localhost:8888`

Swagger: `http://localhost:8888/swagger-ui/index.html`

| Recurso | Ruta base | Operaciones |
|---|---|---|
| Usuarios | `/api/users` | Listar, obtener por id, buscar por correo (`/email/{email}`), crear, actualizar, eliminar |
| Roles | `/api/roles` | Listar, obtener por id, buscar por nombre (`/name/{name}`), crear, actualizar, eliminar |
| Roles de usuario | `/api/user-roles` | Listar, obtener por id, roles de un usuario (`/user/{userId}`), asignar, eliminar |
| Estudiantes | `/api/students` | Listar, obtener por id, buscar por documento (`/document/{documentNumber}`), crear, actualizar, eliminar |
| Docentes | `/api/teachers` | Listar, obtener por id, crear, actualizar, eliminar |
| Programas | `/api/programs` | Listar, obtener por id, crear, actualizar, eliminar |
| Materias | `/api/subjects` | Listar, obtener por id, crear, actualizar, eliminar |
| Grupos | `/api/groups` | Listar, obtener por id, crear, actualizar, eliminar |
| Materias por grupo | `/api/group-subjects` | Listar, obtener por id, materias de un grupo (`/group/{groupId}`), asignar, eliminar |
| Periodos | `/api/periods` | Listar, obtener por id, crear, actualizar, eliminar |
| Matrículas | `/api/enrollments` | Listar, obtener por id, matrículas de un estudiante (`/student/{studentId}`), crear, cambiar estado, eliminar |
| Calificaciones | `/api/grades` | Listar, obtener por id, notas de un estudiante (`/student/{studentId}`), crear, actualizar, eliminar |

### Ejemplos

Crear estudiante (`userId` es opcional):

```json
{
  "firstName": "Laura",
  "lastName": "Gómez",
  "documentNumber": "1001",
  "code": "est-001",
  "birthDate": "2004-03-21",
  "status": "activo",
  "userId": 1
}
```

Matricular (la fecha es opcional, si no se envía queda la de hoy):

```json
{
  "studentId": 1,
  "subjectId": 1,
  "periodId": 1,
  "enrollmentDate": "2026-07-14"
}
```

Cambiar estado de la matrícula (`ACTIVE`, `CANCELLED`, `FINISHED`):

```json
{
  "status": "FINISHED"
}
```

Registrar nota:

```json
{
  "studentId": 1,
  "groupSubjectId": 1,
  "finalScore": 4.3,
  "observation": "Entregó todos los talleres"
}
```

## 📏 Reglas de negocio

- No se repiten: documento y código de estudiantes y docentes, correo e identificación de usuarios, nombre de rol y código de programa, materia y grupo.
- Un usuario no puede tener el mismo rol dos veces y una materia no se asigna dos veces al mismo grupo.
- Un estudiante no puede tener dos matrículas vigentes en la misma materia y periodo (las canceladas no cuentan).
- Una matrícula finalizada no se puede modificar y una cancelada no se puede finalizar.
- La nota va de 0.0 a 5.0 y solo hay una nota por estudiante en cada materia del grupo.
- La fecha de fin del periodo debe ser posterior a la de inicio.

## ⚠️ Respuestas de error

| Código | Cuándo |
|---|---|
| 400 | Datos inválidos, JSON mal formado o regla de negocio incumplida |
| 404 | El recurso no existe |
| 409 | Dato repetido o registro con datos relacionados |

Ejemplo:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "No se encontró un estudiante con el ID: 99",
  "timestamp": "2026-10-17T12:14:43.573"
}
```

## 🚀 Cómo ejecutarlo

### Requisitos

- Java 17 o superior
- Maven, o un IDE como IntelliJ IDEA que lo incluya
- MySQL corriendo en `localhost:3306` (por ejemplo, con XAMPP)

### Pasos

1. Clona el repositorio:

   ```bash
   git clone https://github.com/backyardignas/Integrador-Backend-II.git
   cd Integrador-Backend-II
   ```

2. Crea la base de datos en MySQL (las tablas se crean solas al iniciar):

   ```sql
   CREATE DATABASE bdcesde2025v3;
   ```

3. Revisa el usuario y la contraseña de MySQL en `src/main/resources/application.properties`.

4. Inicia la aplicación:

   ```bash
   mvn spring-boot:run
   ```

   O ejecuta la clase `AplicacionApplication` desde tu IDE. La API queda disponible en `http://localhost:8888`.

## 📸 Evidencias

En la carpeta [`evidencias`](evidencias) están las capturas de las pruebas hechas desde Swagger: creación de cada recurso, validaciones, datos repetidos, cambios de estado de la matrícula y errores 400, 404 y 409.

## 👥 Autores

Proyecto integrador realizado en equipo en CESDE.

- **Santiago Arango Quintero** — [Portafolio](https://backyardignas.github.io) · [LinkedIn](https://www.linkedin.com/in/santi-arango-bb6820267/) · [GitHub](https://github.com/backyardignas)
- **Alex Monroy** — [GitHub](https://github.com/AlexMonroy69)
- **José Vásquez**

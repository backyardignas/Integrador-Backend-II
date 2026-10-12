# 🎓 API de gestión académica — Proyecto integrador Backend II

API REST para administrar la información académica de una institución educativa: usuarios y roles, estudiantes, docentes, programas, materias, grupos, periodos, matrículas y notas.

> Proyecto integrador realizado en equipo de 3 personas en **CESDE**, en el módulo Backend II del programa de Desarrollo de Software.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)

---

## ✨ Qué hace

- **13 entidades** relacionadas que modelan la vida académica: usuarios, roles, estudiantes, docentes, programas, materias, grupos, periodos, matrículas y calificaciones.
- **58 endpoints REST** organizados en 12 controladores.
- Búsquedas útiles además del CRUD: estudiante por número de documento, usuario por correo, rol por nombre, matrículas de un estudiante y materias de un grupo.
- **Manejo global de errores** con excepciones propias para recursos no encontrados y datos inválidos.

## 🧰 Tecnologías

| Área | Herramientas |
|---|---|
| Lenguaje | Java 17 |
| Framework | Spring Boot 3.2 (Spring Web, Spring Data JPA) |
| Base de datos | MySQL |
| Construcción | Maven |

## 🏗️ Estructura

```
src/main/java/co/edu/cesde/ga/aplicacion
├── models        → Entidades JPA (Users, Roles, Students, Teachers, Programs, Subjects, Groups, Periods, Enrollments, Grades…)
├── repository    → Repositorios JPA
├── service       → Interfaces de los servicios
│   └── Impl      → Lógica de negocio
├── controller    → Controladores REST
└── exceptions    → Excepciones propias y manejador global
```

## 📡 Endpoints

URL base: `http://localhost:8080`

| Recurso | Ruta base | Operaciones |
|---|---|---|
| Usuarios | `/api/users` | Listar, obtener por id, buscar por correo (`/email/{email}`), crear, actualizar, eliminar |
| Roles | `/api/roles` | Listar, obtener por id, buscar por nombre (`/name/{name}`), crear, eliminar |
| Roles de usuario | `/api/user-roles` | Listar, roles de un usuario (`/user/{userId}`), asignar y quitar (`/{userId}/{roleId}`) |
| Estudiantes | `/api/students` | Listar, obtener por id, buscar por documento (`/document/{documentNumber}`), crear, actualizar, eliminar |
| Docentes | `/api/teachers` | Listar, obtener por id, crear, actualizar, eliminar |
| Programas | `/api/programs` | Listar, crear |
| Materias | `/api/subjects` | Listar, obtener por id, crear, actualizar, eliminar |
| Grupos | `/api/groups` | Listar, obtener por id, crear, actualizar, eliminar |
| Materias por grupo | `/api/group-subjects` | Listar, obtener por id, materias de un grupo (`/group/{groupId}`), crear, eliminar |
| Periodos | `/api/periods` | Listar, obtener por id, crear, actualizar, eliminar |
| Matrículas | `/api/enrollments` | Listar, obtener por id, matrículas de un estudiante (`/student/{studentId}`), crear, eliminar |
| Calificaciones | `/api/grades` | Listar, obtener por id, crear, actualizar, eliminar |

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

   O ejecuta la clase `AplicacionApplication` desde tu IDE. La API queda disponible en `http://localhost:8080`.

## 👥 Autores

Proyecto integrador realizado en equipo en CESDE.

- **Santiago Arango Quintero** — [Portafolio](https://backyardignas.github.io) · [LinkedIn](https://www.linkedin.com/in/santi-arango-bb6820267/) · [GitHub](https://github.com/backyardignas)
- **Alex Monroy** — [GitHub](https://github.com/AlexMonroy69)
- **José Vásquez**

# Proyecto Integrador Backend II - CESDE

API REST para la gestión académica: usuarios, roles, estudiantes, profesores, programas, periodos, materias, grupos, matrículas y notas.

**Stack:** Java 17, Spring Boot 3, Spring Data JPA, MySQL (XAMPP), Swagger.

## Cómo ejecutarlo

1. Iniciar MySQL desde XAMPP y crear la base de datos `bdcesde2025v3`.
2. Revisar usuario y contraseña en `src/main/resources/application.properties`.
3. Ejecutar `AplicacionApplication` desde IntelliJ.

La API queda en `http://localhost:8888` y la documentación en `http://localhost:8888/swagger-ui/index.html`.

## Estructura

```
application/service        interfaces de los servicios
application/service/Impl   implementación y reglas de negocio
controller                 controladores REST
controller/dto             DTO de entrada (Create / Update)
controller/dto/response    DTO de salida y ErrorResponse
domain                     entidades JPA y EnrollmentStatus
domain/exceptions          excepciones del negocio
domain/repository          repositorios JPA
presentation/advice        manejo global de errores
```

## Endpoints

Todos los recursos tienen `GET` (listar), `GET /{id}`, `POST`, `PUT /{id}` y `DELETE /{id}`, excepto donde se indica.

| Recurso | Ruta | Extra |
|---------|------|-------|
| Usuarios | `/api/users` | `GET /email/{email}` |
| Roles | `/api/roles` | `GET /name/{name}` |
| Roles de usuario | `/api/user-roles` | `GET /user/{userId}`, sin `PUT` |
| Estudiantes | `/api/students` | |
| Profesores | `/api/teachers` | |
| Programas | `/api/programs` | |
| Periodos | `/api/periods` | |
| Materias | `/api/subjects` | |
| Grupos | `/api/groups` | |
| Materias por grupo | `/api/group-subjects` | `GET /group/{groupId}`, sin `PUT` |
| Notas | `/api/grades` | `GET /student/{studentId}` |
| Matrículas | `/api/enrollments` | `GET /student/{studentId}`, el `PUT` cambia el estado |

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

## Reglas de negocio

- No se repiten: documento y código de estudiantes y profesores, correo e identificación de usuarios, nombre de rol, código de programa, materia y grupo.
- Un usuario no puede tener el mismo rol dos veces y una materia no se asigna dos veces al mismo grupo.
- Un estudiante no puede tener dos matrículas vigentes en la misma materia y periodo (las canceladas no cuentan).
- Una matrícula finalizada no se puede modificar y una cancelada no se puede finalizar.
- La nota va de 0.0 a 5.0 y solo hay una nota por estudiante en cada materia del grupo.
- La fecha de fin del periodo debe ser posterior a la de inicio.

## Respuestas de error

| Código | Cuándo |
|--------|--------|
| 400 | Datos inválidos, JSON mal formado o regla de negocio incumplida |
| 404 | El recurso no existe |
| 409 | Dato repetido o registro con datos relacionados |

Las evidencias de las pruebas están en la carpeta `evidencias`.

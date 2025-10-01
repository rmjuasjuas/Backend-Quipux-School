# Sistema de Gestión Escolar - API REST

Proyecto académico desarrollado para la Universidad (UCO) que implementa servicios web REST para la gestión de estudiantes, materias y sus relaciones.

## Descripción

Sistema web backend que permite:
- Gestionar estudiantes (consultar, registrar)
- Gestionar materias (consultar, registrar)
- Inscribir estudiantes en materias
- Consultar la relación estudiantes-materias mediante JOIN de bases de datos

## Tecnologías Utilizadas

- **Java 8+**
- **JAX-RS** (Java API for RESTful Web Services)
- **Gson** (serialización/deserialización JSON)
- **Apache Tomcat 8.5+** (servidor de aplicaciones)
- **MySQL/MariaDB** (base de datos)
- **Maven** (gestión de dependencias)
- **Eclipse IDE**

## Estructura del Proyecto

```
Colegio/
├── src/main/java/
│   ├── co.com.bd/
│   │   └── BaseDatos.java          # Conexión a base de datos
│   ├── co.com.manager/
│   │   ├── EstudiantesManager.java # Lógica de negocio de estudiantes
│   │   └── MateriasManager.java    # Lógica de negocio de materias
│   ├── co.com.model/
│   │   ├── Estudiante.java         # Modelo Estudiante
│   │   ├── Materia.java            # Modelo Materia
│   │   ├── MateriaInput.java       # DTO para entrada de datos
│   │   └── EstudianteMateria.java  # Modelo para JOIN
│   ├── co.com.ws.rest/
│   │   ├── WsColegio.java          # Servicios REST generales
│   │   └── WSEstudiante.java       # Servicios REST de estudiantes
│   └── launch/
│       └── Main.java               # Clase principal para iniciar Tomcat
├── src/main/resources/
│   └── app.properties              # Configuración de base de datos
└── src/main/webapp/
    └── WEB-INF/
        └── web.xml                 # Configuración de la aplicación web
```

## Notas Importantes

- El puerto por defecto es **8081** (configurable en `Main.java`)
- La base de datos usa **AUTO_INCREMENT** para IDs
- Todos los servicios POST consumen y producen JSON
- Se implementa el patrón Manager para separar lógica de negocio

## Autor

Proyecto desarrollado como tarea académica, hecho con mucho amor de parte de:
  Jose Miguel Rios Montoya.

  ## ¿Preguntas y Contacto?

Correo: jomirimo09@hotmail.com

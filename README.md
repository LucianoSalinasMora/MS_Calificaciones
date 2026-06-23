MS_Calificaciones
Encargado del registro, almacenamiento y control del historial de notas parciales y finales de los estudiantes en sus respectivas asignaturas cursadas.

Especificaciones Técnicas
Puerto: 8004

Base de Datos (Laragon): calificaciones_db

Tecnologías: Spring Boot 3.4.1, WebClient, Spring Data JPA, Springdoc OpenAPI (Swagger), JUnit 5, Mockito

Regla de Negocio Crítica
Si una nota registrada es menor a 4.0 y se marca como esNotaFinal: true, este servicio gatilla una llamada síncrona vía WebClient al servicio de Arancel (8006) para reportar el retraso académico inmediato del alumno. Las rutas del servicio y de las pruebas apuntan dinámicamente al microservicio de destino.

Estructura de Desarrollo y Pruebas
Estructura de Carpetas: Configuración global de Swagger bajo el paquete config. Pruebas unitarias organizadas en las carpetas controller y service dentro del directorio src/test/java/SigueTuCarrera/.

Carga Inicial: Archivo SQL configurado al lado de application.properties utilizando sentencias INSERT IGNORE INTO.

Endpoints Principales
POST /auth/Calificacioness - Registrar una nueva calificación parcial o final.

GET /Calificacioness/estudiante/{rut} - Obtener el historial completo de notas de un alumno.

GET /swagger-ui.html - Documentación interactiva de la API.

Compilación y Despliegue Docker
Bash
mvn clean package
docker build -t ms-calificaciones:1.0 .
docker run -d -p 8004:8004 --name ms-calificaciones -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker

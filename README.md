# MS_Calificaciones
Encargado del registro, almacenamiento y control del historial de notas parciales y finales de los estudiantes en sus respectivas asignaturas cursadas.

## Especificaciones Técnicas
* **Puerto:** `8004`
* **Base de Datos (Laragon):** `calificaciones_db`
* **Tecnologías:** Spring Boot 4.0.6, WebClient

## Regla de Negocio Crítica
* Si una nota registrada es menor a 4.0 y se marca como "esNotaFinal": true, este servicio gatilla una llamada síncrona vía WebClient al servicio de Arancel (8006) para reportar el retraso académico inmediato del alumno.

## Endpoints Principales
* `POST /api/v1/Calificacioness` - Registrar una nueva calificación parcial o final.
* `GET /api/v1/Calificacioness/estudiante/{rut}` - Obtener el historial completo de notas de un alumno.

package SigueTuCarrera.Calificaciones.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@Schema(description = "Modelo que representa el registro de calificaciones y evaluaciones de los estudiantes")
public class Calificaciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del registro de la nota", example = "4512")
    private Long notaId;
    
    @Schema(description = "Identificador exclusivo del estudiante", example = "EST-2026-88")
    private String estudianteId;

    @Schema(description = "Código identificador de la asignatura o curso", example = "ASY4131")
    private String asignaturaCodigo;

    @Schema(description = "Periodo académico correspondiente a la evaluación", example = "2026-1")
    private String periodoAcademico;
    
    @Schema(description = "Valor numérico obtenido en la calificación", example = "6.5")
    private Double valorNota;

    @Schema(description = "Porcentaje de peso o ponderación de la nota en el curso", example = "0.25")
    private Double ponderacion;

    @Schema(description = "Indica si la calificación corresponde al promedio final calculado de la asignatura", example = "false")
    private Boolean esNotaFinal;
    
    @Enumerated(EnumType.STRING)
    @Schema(description = "Tipo o formato de la evaluación aplicada", example = "SOLEMNE")
    private TipoEvaluacion tipoEvaluacion;

    public enum TipoEvaluacion {
        SOLEMNE, CONTROL, TALLER, EXAMEN, NOTA_FINAL
    }
}
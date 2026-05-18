package SigueTuCarrera.Calificaciones.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Calificaciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notaId;
    
    private String estudianteId;
    private String asignaturaCodigo;
    private String periodoAcademico;
    
    private Double valorNota;
    private Double ponderacion;
    private Boolean esNotaFinal;
    
    @Enumerated(EnumType.STRING)
    private TipoEvaluacion tipoEvaluacion;

    public enum TipoEvaluacion {
        SOLEMNE, CONTROL, TALLER, EXAMEN, NOTA_FINAL
    }
}

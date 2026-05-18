package SigueTuCarrera.Calificaciones.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import SigueTuCarrera.Calificaciones.model.Calificaciones;
import SigueTuCarrera.Calificaciones.repository.CalificacionesRepository;
import jakarta.transaction.Transactional;
@Service
@Transactional
public class CalificacionesService {
    @Autowired
    private CalificacionesRepository repository;

    @Autowired
    private WebClient arancelWebClient;

    public Calificaciones saveCalificaciones(Calificaciones calificaciones) {
        Calificaciones notaGuardada = repository.save(calificaciones);

        if (Boolean.TRUE.equals(notaGuardada.getEsNotaFinal()) && notaGuardada.getValorNota() < 4.0) {
            arancelWebClient.post()
                    .uri("/api/v1/tuition/report-delay/{rut}", notaGuardada.getEstudianteId())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        }
        
        return notaGuardada;
    }

    public List<Calificaciones> getStudentCalificaciones(String rut) {
        return repository.findByEstudianteId(rut);
    }
}


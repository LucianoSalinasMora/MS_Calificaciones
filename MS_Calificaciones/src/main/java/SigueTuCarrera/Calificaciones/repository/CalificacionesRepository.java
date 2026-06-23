package SigueTuCarrera.Calificaciones.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SigueTuCarrera.Calificaciones.model.Calificaciones;

public interface CalificacionesRepository extends JpaRepository<Calificaciones, Long>{
    List<Calificaciones> findByEstudianteId(String rut);
}

package SigueTuCarrera.Calificaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SigueTuCarrera.Calificaciones.model.Calificaciones;
import SigueTuCarrera.Calificaciones.service.CalificacionesService;

@RestController
@RequestMapping("/api/v1/Calificacioness")
public class CalificacionesController {
    @Autowired
    private CalificacionesService service;

    @PostMapping
    public Calificaciones addCalificaciones(@RequestBody Calificaciones Calificaciones){
        return service.saveCalificaciones(Calificaciones);
    }

    @GetMapping("/student/{rut}")
    public List<Calificaciones> getByStudent(@PathVariable String rut) {
        return service.getStudentCalificaciones(rut);
    }
}

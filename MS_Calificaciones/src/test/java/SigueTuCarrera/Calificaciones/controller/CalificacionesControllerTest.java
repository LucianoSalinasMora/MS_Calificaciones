package SigueTuCarrera.Calificaciones.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import SigueTuCarrera.Calificaciones.model.Calificaciones;
import SigueTuCarrera.Calificaciones.service.CalificacionesService;

@WebMvcTest(CalificacionesController.class)
class CalificacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalificacionesService service;

    @Autowired
    private ObjectMapper objectMapper;

    private Calificaciones calificacionPrueba;

    @BeforeEach
    void setUp() {
        calificacionPrueba = new Calificaciones();
        calificacionPrueba.setEstudianteId("20123456-K");
        calificacionPrueba.setEsNotaFinal(true);
        calificacionPrueba.setValorNota(6.8);
    }

    @Test
    void testAddCalificaciones_RetornaCalificacionGuardada() throws Exception {
        when(service.saveCalificaciones(any(Calificaciones.class))).thenReturn(calificacionPrueba);

        mockMvc.perform(post("/api/v1/Calificacioness")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(calificacionPrueba)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estudianteId").value("20123456-K"))
                .andExpect(jsonPath("$.valorNota").value(6.8));
    }

    @Test
    void testGetByStudent_RetornaListaDeCalificaciones() throws Exception {
        when(service.getStudentCalificaciones("20123456-K")).thenReturn(Arrays.asList(calificacionPrueba));

        mockMvc.perform(get("/api/v1/Calificacioness/student/20123456-K"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estudianteId").value("20123456-K"))
                .andExpect(jsonPath("$[0].valorNota").value(6.8));
    }
}
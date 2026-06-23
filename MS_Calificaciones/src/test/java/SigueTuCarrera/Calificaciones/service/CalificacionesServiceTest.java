package SigueTuCarrera.Calificaciones.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import SigueTuCarrera.Calificaciones.model.Calificaciones;
import SigueTuCarrera.Calificaciones.repository.CalificacionesRepository;

@ExtendWith(MockitoExtension.class)
class CalificacionesServiceTest {

    @Mock
    private CalificacionesRepository repository;

    @Mock
    private WebClient arancelWebClient;

    // Mocks encadenados para la interfaz fluida de WebClient
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;
    @Mock
    private WebClient.RequestBodySpec requestBodySpec;
    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private CalificacionesService service;

    private Calificaciones notaAprobada;
    private Calificaciones notaReprobadaFinal;

    @BeforeEach
    void setUp() {
        
        notaAprobada = new Calificaciones();
        notaAprobada.setEstudianteId("20123456-K");
        notaAprobada.setEsNotaFinal(true);
        notaAprobada.setValorNota(5.5);

        
        notaReprobadaFinal = new Calificaciones();
        notaReprobadaFinal.setEstudianteId("20123456-K");
        notaReprobadaFinal.setEsNotaFinal(true);
        notaReprobadaFinal.setValorNota(3.5);
    }

    @Test
    void testSaveCalificaciones_NotaAprobada_NoLlamaArancel() {
        when(repository.save(any(Calificaciones.class))).thenReturn(notaAprobada);

        Calificaciones resultado = service.saveCalificaciones(notaAprobada);

        assertNotNull(resultado);
        assertEquals(5.5, resultado.getValorNota());
        
        verify(arancelWebClient, never()).post();
    }

    @Test
    void testSaveCalificaciones_NotaReprobadaFinal_LlamaArancel() {
        when(repository.save(any(Calificaciones.class))).thenReturn(notaReprobadaFinal);

        
        when(arancelWebClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(anyString(), any(Object[].class))).thenReturn(requestBodySpec);
        when(requestBodySpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Void.class)).thenReturn(Mono.empty()); 

        Calificaciones resultado = service.saveCalificaciones(notaReprobadaFinal);

        assertNotNull(resultado);
        assertEquals(3.5, resultado.getValorNota());
        // Verificamos que se ejecutó la llamada externa
        verify(arancelWebClient, times(1)).post();
    }

    @Test
    void testGetStudentCalificaciones() {
        List<Calificaciones> listaMock = Arrays.asList(notaAprobada, notaReprobadaFinal);
        when(repository.findByEstudianteId("20123456-K")).thenReturn(listaMock);

        List<Calificaciones> resultado = service.getStudentCalificaciones("20123456-K");

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(repository, times(1)).findByEstudianteId("20123456-K");
    }
}
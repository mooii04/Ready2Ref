package com.salesianos.triana.DoradoMoises_Ready2Ref;

import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.EditMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.dto.mensaje.GetMensajeDto;
import com.salesianos.triana.DoradoMoises_Ready2Ref.model.Mensaje;
import com.salesianos.triana.DoradoMoises_Ready2Ref.repository.MensajeRepository;
import com.salesianos.triana.DoradoMoises_Ready2Ref.service.MensajeService;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.MensajeSpecification;
import com.salesianos.triana.DoradoMoises_Ready2Ref.specification.SearchCriteria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MensajeServiceTest {

    @Mock
    private MensajeRepository mensajeRepository;

    @InjectMocks
    private MensajeService mensajeService;

    @Test
    void testGuardarMensaje() {
        EditMensajeDto dto = new EditMensajeDto("Entrenamiento subido", "Se ha subido un nuevo entrenamiento a la plataforma", LocalDate.now(), false);
        Mensaje mensaje = Mensaje.builder()
                .asunto(dto.asunto())
                .contenido(dto.contenido())
                .fechaEnvio(LocalDate.now())
                .leido(false)
                .build();

        when(mensajeRepository.save(ArgumentMatchers.any(Mensaje.class))).thenReturn(mensaje);

        Mensaje resultado = mensajeService.save(dto);

        assertNotNull(resultado);
        assertEquals(dto.asunto(), resultado.getAsunto());
        assertEquals(dto.contenido(), resultado.getContenido());
        Mockito.verify(mensajeRepository, times(1)).save(ArgumentMatchers.any(Mensaje.class));
    }

    @Test
    void testBuscarMensajes() {
        List<SearchCriteria> criterios = List.of(new SearchCriteria("asunto", "eq", "Entrenamiento subido"));
        MensajeSpecification specification = new MensajeSpecification(criterios);
        Specification<Mensaje> where = specification.build();

        Mensaje mensaje = Mensaje.builder()
                .asunto("Entrenamiento subido")
                .contenido("Se ha subido un nuevo entrenamiento a la plataforma")
                .fechaEnvio(LocalDate.now())
                .leido(false)
                .build();

        when(mensajeRepository.findAll(ArgumentMatchers.any(Specification.class))).thenReturn(List.of(mensaje));

        List<GetMensajeDto> resultado = mensajeService.buscarMensajes(criterios);

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals("Entrenamiento subido", resultado.get(0).asunto());
        Mockito.verify(mensajeRepository, times(1)).findAll(ArgumentMatchers.any(Specification.class));
    }

    @Test
    void testEnviarMensajeEntrenoSubido() {
        when(mensajeRepository.save(ArgumentMatchers.any(Mensaje.class))).thenReturn(new Mensaje());

        mensajeService.enviarMensajeEntrenoSubido();

        Mockito.verify(mensajeRepository, times(1)).save(ArgumentMatchers.any(Mensaje.class));
    }
}
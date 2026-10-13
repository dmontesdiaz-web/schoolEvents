package es.safareyes.schoolevents;

import es.safareyes.schoolevents.dtos.InformeParticipacion;
import es.safareyes.schoolevents.modelos.Inscripcion;
import es.safareyes.schoolevents.repositorios.iInscripcionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestInscripcionRepository {

    @Autowired
    private iInscripcionRepository repository;

    @Test
    void consultarInscritosEvento() {
        Page<Inscripcion> pagina = repository.findByEventoId(1, PageRequest.of(0, 10));
    }

    @Test
    void consultarInformeParticipacion() {
        List<InformeParticipacion> todos = repository.informeParticipacion(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 12, 31));
    }
}

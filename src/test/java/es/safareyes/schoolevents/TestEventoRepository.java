package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.EstadoInscripcion;
import es.safareyes.schoolevents.modelos.Evento;
import es.safareyes.schoolevents.repositorios.iEventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestEventoRepository {

    @Autowired
    private iEventoRepository repository;

    @Test
    void consultarEventosPorTitulo() {
        Page<Evento> pagina = repository.findByTituloContainingIgnoreCase("a", PageRequest.of(0, 5));
    }

    @Test
    void consultarCalendario() {
        List<Evento> todos = repository.findCalendario(10, 2026);
    }

    @Test
    void consultarDetalleEvento() {
        Optional<Evento> evento = repository.findDetalleById(1);
    }

    @Test
    void consultarAgendaUsuario() {
        List<Evento> todos = repository.findAgendaUsuario(1, EstadoInscripcion.CONFIRMADA);
    }
}

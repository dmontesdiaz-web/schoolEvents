package es.safareyes.schoolevents;

import es.safareyes.schoolevents.modelos.Lugar;
import es.safareyes.schoolevents.repositorios.iLugarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestLugarRepository {

    @Autowired
    private iLugarRepository repository;

    @Test
    void consultarLugares() {
        List<Lugar> todos = repository.findAllByOrderByNombreAsc();
    }
}

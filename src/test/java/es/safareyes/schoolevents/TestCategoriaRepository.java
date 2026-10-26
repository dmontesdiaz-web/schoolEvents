package es.safareyes.schoolevents;

import es.safareyes.schoolevents.entity.Categoria;
import es.safareyes.schoolevents.repository.iCategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestCategoriaRepository {

    @Autowired
    private iCategoriaRepository repository;

    @Test
    void consultarCategorias() {
        List<Categoria> todos = repository.findAllByOrderByNombreAsc();
    }
}

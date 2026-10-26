package es.safareyes.schoolevents;

import es.safareyes.schoolevents.entity.FotoPublicacion;
import es.safareyes.schoolevents.repository.iFotoPublicacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestFotoPublicacionRepository {

    @Autowired
    private iFotoPublicacionRepository repository;

    @Test
    void consultarFotosPublicacion() {
        List<FotoPublicacion> todos = repository.findFotosDePublicacion(1);
    }
}

package es.safareyes.schoolevents;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Ejecuta todos los tests de repositorios de una vez
@Suite
@SelectClasses({
        TestEventoRepository.class,
        TestInscripcionRepository.class,
        TestCategoriaRepository.class,
        TestLugarRepository.class,
        TestFotoPublicacionRepository.class
})
class SchoolEventsApplicationTests {
}

package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface iCategoriaRepository extends JpaRepository<Categoria, Integer> {


    /**
     * Usando JPA Interfaz (endpoint 7)
     *
     * @return
     */
    List<Categoria> findAllByOrderByNombreAsc();
}

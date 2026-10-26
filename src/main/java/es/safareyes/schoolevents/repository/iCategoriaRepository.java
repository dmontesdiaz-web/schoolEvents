package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.Categoria;
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

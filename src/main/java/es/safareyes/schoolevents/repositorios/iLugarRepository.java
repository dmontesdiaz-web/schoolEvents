package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface iLugarRepository extends JpaRepository<Lugar, Integer> {

    List<Lugar> findAllByOrderByNombreAsc();
}

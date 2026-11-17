package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Lugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface iLugarRepository extends JpaRepository<Lugar, Integer> {

    List<Lugar> findAllByOrderByNombreAsc();
}

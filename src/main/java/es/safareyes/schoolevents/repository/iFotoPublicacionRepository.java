package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.FotoPublicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface iFotoPublicacionRepository extends JpaRepository<FotoPublicacion, Integer> {

    @Query("SELECT f FROM FotoPublicacion f JOIN FETCH f.publicacion WHERE f.publicacion.id = :publicacionId")
    List<FotoPublicacion> findFotosDePublicacion(Integer publicacionId);
}

package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Publicacion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iPublicacionRepository extends JpaRepository<Publicacion, Integer> {

    // Publicaciones de un evento con su autor y sus fotos en orden
    @Query("""
            SELECT p FROM Publicacion p
                JOIN FETCH p.usuario
                LEFT JOIN FETCH p.fotos f
            WHERE p.evento.id = :eventoId
            ORDER BY p.creadaEn DESC, f.orden
            """)
    List<Publicacion> buscarPorEventoConFotos(@Param("eventoId") Integer eventoId);
}

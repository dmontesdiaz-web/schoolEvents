package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Publicacion;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iPublicacionRepository extends JpaRepository<Publicacion, Integer> {
    @EntityGraph(attributePaths = {"usuarios", "fotos"})
    List<Publicacion> findByEventoIdOrderByCreadaEnDesc(Integer eventoId);
}

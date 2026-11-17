package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPublicacionRepository extends JpaRepository<Publicacion, Integer> {
}

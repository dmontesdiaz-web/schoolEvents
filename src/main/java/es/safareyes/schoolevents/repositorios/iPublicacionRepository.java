package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPublicacionRepository extends JpaRepository<Publicacion, Integer> {
}

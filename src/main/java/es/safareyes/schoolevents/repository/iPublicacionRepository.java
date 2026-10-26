package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iPublicacionRepository extends JpaRepository<Publicacion, Integer> {
}

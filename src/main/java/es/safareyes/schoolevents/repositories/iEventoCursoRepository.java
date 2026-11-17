package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.EventoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iEventoCursoRepository extends JpaRepository<EventoCurso, Integer> {
}

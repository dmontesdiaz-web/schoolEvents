package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.EventoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iEventoCursoRepository extends JpaRepository<EventoCurso, Integer> {
}

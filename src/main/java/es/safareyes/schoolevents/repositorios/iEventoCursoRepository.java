package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.EventoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iEventoCursoRepository extends JpaRepository<EventoCurso, Integer> {
}

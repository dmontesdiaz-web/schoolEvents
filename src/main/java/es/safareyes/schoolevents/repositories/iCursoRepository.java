package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCursoRepository extends JpaRepository<Curso, Integer> {
}

package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCursoRepository extends JpaRepository<Curso, Integer> {
}

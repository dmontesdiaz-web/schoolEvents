package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.dtos.InformeParticipacion;
import es.safareyes.schoolevents.modelos.Inscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.*;

@Repository
public interface iInscripcionRepository extends JpaRepository<Inscripcion, Integer> {
    Page<Inscripcion> findByEventoId(Integer eventoId, Pageable pageable);

    @Query("SELECT new es.safareyes.schoolevents.dtos.InformeParticipacion(e.categoria.nombre, u.curso.nombre, COUNT(i)) " +
            "FROM Inscripcion i JOIN i.evento e JOIN i.usuario u " +
            "WHERE e.fechaInicio BETWEEN :desde AND :hasta GROUP BY e.categoria.nombre, u.curso.nombre")
    List<InformeParticipacion> informeParticipacion(LocalDate desde, LocalDate hasta);
}

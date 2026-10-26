package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.Evento;
import es.safareyes.schoolevents.entity.TipoInscripcion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface iEventoRepository extends JpaRepository<Evento, Integer> {

    Page<Evento> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @Query("SELECT e FROM Evento e JOIN FETCH e.categoria WHERE MONTH(e.fechaInicio) = :mes AND YEAR(e.fechaInicio) = :anio ORDER BY e.fechaInicio")
    List<Evento> findCalendario(Integer mes, Integer anio);

    @Query("SELECT e FROM Evento e JOIN FETCH e.lugar JOIN FETCH e.categoria WHERE e.id = :id")
    Optional<Evento> findDetalleById(Integer id);

    @Query("SELECT i.evento FROM Inscripcion i WHERE i.usuario.id = :usuarioId AND i.tipo = :tipo")
    List<Evento> findAgendaUsuario(Integer usuarioId, TipoInscripcion tipo);
}

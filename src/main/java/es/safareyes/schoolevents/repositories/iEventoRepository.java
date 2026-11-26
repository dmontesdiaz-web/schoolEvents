package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.EstadoEvento;
import es.safareyes.schoolevents.entities.Evento;
import es.safareyes.schoolevents.entities.EventoCurso;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface iEventoRepository extends JpaRepository<Evento, Integer>, JpaSpecificationExecutor<Evento> {
    List<Evento> findByTituloContainingIgnoreCase(String titulo);

    @Query("""
            SELECT e FROM Evento e
                JOIN FETCH e.lugar
                JOIN FETCH e.categoria
            WHERE e.estado IN (es.safareyes.schoolevents.entities.EstadoEvento.PUBLICADO,
                               es.safareyes.schoolevents.entities.EstadoEvento.FINALIZADO)
              AND (:idCategoria IS NULL OR e.categoria.id = :idCategoria)
              AND (:texto IS NULL OR LOWER(e.titulo) LIKE :texto OR LOWER(e.descripcion) LIKE :texto)
              AND (:desde IS NULL OR e.fechaInicio >= :desde)
              AND (:hasta IS NULL OR e.fechaInicio <= :hasta)
              AND (:idCurso IS NULL
                   OR EXISTS (SELECT ec FROM EventoCurso ec WHERE ec.evento = e AND ec.curso.id = :idCurso)
                   OR NOT EXISTS (SELECT ec2 FROM EventoCurso ec2 WHERE ec2.evento = e))
            ORDER BY e.fechaInicio, e.horaInicio
            """)
    List<Evento> buscarConFiltros(@Param("idCategoria") Integer idCategoria,
                                  @Param("idCurso") Integer idCurso,
                                  @Param("texto") String texto,
                                  @Param("desde") LocalDate desde,
                                  @Param("hasta") LocalDate hasta);


    @EntityGraph(attributePaths = {"lugar", "categoria", "organizador", "cursos", "cursos.curso"})
    Optional<Evento> findDetalleById(Integer id);

    @Query("""
            SELECT e FROM Evento e
                JOIN FETCH e.lugar
                JOIN FETCH e.categoria
            WHERE e.fechaInicio BETWEEN :inicio AND :fin
              AND e.estado IN (es.safareyes.schoolevents.entities.EstadoEvento.PUBLICADO,
                               es.safareyes.schoolevents.entities.EstadoEvento.FINALIZADO)
            ORDER BY e.fechaInicio, e.horaInicio
            """)
    List<Evento> buscarCalendario(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query(nativeQuery = true, value = """
            SELECT EXISTS (
                SELECT 1 FROM evento e
                WHERE e.id_lugar = :idLugar
                  AND e.estado <> 'CANCELADO'
                  AND e.id <> :idExcluido
                  AND (e.fecha_inicio + e.hora_inicio) < :fin
                  AND (e.fecha_fin + e.hora_fin) > :inicio)
            """)
    boolean existeSolape(@Param("idLugar") Integer idLugar,
                         @Param("inicio") LocalDateTime inicio,
                         @Param("fin") LocalDateTime fin,
                         @Param("idExcluido") Integer idExcluido);

    @Query("""
            SELECT e FROM Evento e
            WHERE e.estado = es.safareyes.schoolevents.entities.EstadoEvento.PUBLICADO
              AND (e.fechaFin < :hoy OR (e.fechaFin = :hoy AND e.horaFin <= :ahora))
            """)
    List<Evento> buscarTerminados(@Param("hoy") LocalDate hoy, @Param("ahora") LocalTime ahora);

}

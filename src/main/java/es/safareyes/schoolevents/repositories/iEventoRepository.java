package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.EstadoEvento;
import es.safareyes.schoolevents.entities.Evento;
import es.safareyes.schoolevents.dtos.EventoCalendarioView;
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

    Page<Evento> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"lugar", "categoria"})
    Page<Evento> findAll(Specification<Evento> spec, Pageable pageable);

    @EntityGraph(attributePaths = {"lugar", "categoria", "organizador", "cursos", "cursos.curso"})
    Optional<Evento> findDetalleById(Integer id);

    @Query(value = """
            SELECT e.id AS id, e.titulo AS titulo, e.fecha_inicio AS dia,
                   e.hora_inicio AS horaInicio, e.hora_fin AS horaFin,
                   l.nombre AS lugar, c.nombre AS categoria, c.icono AS icono, c.color AS color
            FROM evento e
                JOIN lugar l ON l.id = e.id_lugar
                JOIN categoria c ON c.id = e.id_categoria
            WHERE EXTRACT(MONTH FROM e.fecha_inicio) = :mes
              AND EXTRACT(YEAR FROM e.fecha_inicio) = :anio
              AND e.estado IN ('PUBLICADO', 'FINALIZADO')
            ORDER BY e.fecha_inicio, e.hora_inicio
            """, nativeQuery = true)
    List<EventoCalendarioView> findCalendario(@Param("mes") Integer mes, @Param("anio") Integer anio);

    @Query(value = """
            SELECT EXISTS (
                SELECT 1 FROM evento e
                WHERE e.id_lugar = :idLugar
                  AND e.estado <> 'CANCELADO'
                  AND e.id <> :idExcluido
                  AND (e.fecha_inicio + e.hora_inicio) < :fin
                  AND (e.fecha_fin + e.hora_fin) > :inicio)
            """, nativeQuery = true)
    boolean existeSolape(@Param("idLugar") Integer idLugar, @Param("inicio") LocalDateTime inicio,
                         @Param("fin") LocalDateTime fin, @Param("idExcluido") Integer idExcluido);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            UPDATE Evento e SET e.estado = es.safareyes.schoolevents.entities.EstadoEvento.FINALIZADO
            WHERE e.estado = es.safareyes.schoolevents.entities.EstadoEvento.PUBLICADO
              AND (e.fechaFin < :hoy OR (e.fechaFin = :hoy AND e.horaFin <= :ahora))
            """)
    int finalizarTerminados(@Param("hoy") LocalDate hoy, @Param("ahora") LocalTime ahora);

    static Specification<Evento> filtrar(Integer idCategoria, Integer idCurso, String texto,
                                         LocalDate desde, LocalDate hasta) {
        return (root, query, cb) -> {
            List<Predicate> condiciones = new ArrayList<>();

            // RN-02: el público solo ve eventos publicados y finalizados
            condiciones.add(root.get("estado").in(EstadoEvento.PUBLICADO, EstadoEvento.FINALIZADO));

            if (idCategoria != null) {
                condiciones.add(cb.equal(root.get("categoria").get("id"), idCategoria));
            }
            if (texto != null && !texto.isBlank()) {
                String patron = "%" + texto.trim().toLowerCase() + "%";
                condiciones.add(cb.or(
                        cb.like(cb.lower(root.get("titulo")), patron),
                        cb.like(cb.lower(root.get("descripcion")), patron)));
            }
            if (desde != null) {
                condiciones.add(cb.greaterThanOrEqualTo(root.get("fechaInicio"), desde));
            }
            if (hasta != null) {
                condiciones.add(cb.lessThanOrEqualTo(root.get("fechaInicio"), hasta));
            }
            // Eventos visibles para un curso: los de ese curso y los abiertos a todos (RN-03)
            if (idCurso != null) {
                Subquery<Integer> delCurso = query.subquery(Integer.class);
                var ec = delCurso.from(EventoCurso.class);
                delCurso.select(ec.get("id")).where(
                        cb.equal(ec.get("evento"), root),
                        cb.equal(ec.get("curso").get("id"), idCurso));

                Subquery<Integer> conCursos = query.subquery(Integer.class);
                var cualquiera = conCursos.from(EventoCurso.class);
                conCursos.select(cualquiera.get("id")).where(cb.equal(cualquiera.get("evento"), root));

                condiciones.add(cb.or(cb.exists(delCurso), cb.not(cb.exists(conCursos))));
            }
            return cb.and(condiciones.toArray(Predicate[]::new));
        };
    }

}

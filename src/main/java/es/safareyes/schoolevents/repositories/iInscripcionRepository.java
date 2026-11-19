package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.dtos.ParticipacionDTO;
import es.safareyes.schoolevents.entities.EstadoInscripcion;
import es.safareyes.schoolevents.entities.Inscripcion;
import es.safareyes.schoolevents.entities.TipoInscripcion;
import es.safareyes.schoolevents.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface iInscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    @EntityGraph(attributePaths = {"usuario", "evento"})
    Page<Inscripcion> findByEventoId(Integer eventoId, Pageable pageable);

    Optional<Inscripcion> findByUsuarioIdAndEventoId(Integer usuarioId, Integer eventoId);

    long countByEventoIdAndEstado(Integer eventoId, EstadoInscripcion estado);

    Optional<Inscripcion> findFirstByEventoIdAndEstadoOrderByPosicionEsperaAsc(Integer eventoId,
                                                                               EstadoInscripcion estado);

    Optional<Inscripcion> findTopByEventoIdAndEstadoOrderByPosicionEsperaDesc(Integer eventoId,
                                                                              EstadoInscripcion estado);

    boolean existsByUsuarioIdAndEventoIdAndAsistioTrue(Integer usuarioId, Integer eventoId);

    @Query("""
            SELECT i FROM Inscripcion i
                JOIN FETCH i.evento e
                JOIN FETCH e.lugar
                JOIN FETCH e.categoria
            WHERE i.usuario.id = :usuarioId
              AND (:tipo IS NULL OR i.tipo = :tipo)
            ORDER BY e.fechaInicio, e.horaInicio
            """)
    List<Inscripcion> findAgenda(@Param("usuarioId") Integer usuarioId, @Param("tipo") TipoInscripcion tipo);

    @Query("SELECT i.usuario FROM Inscripcion i WHERE i.evento.id = :eventoId")
    List<Usuario> findUsuariosInscritos(@Param("eventoId") Integer eventoId);

    @Query("""
            SELECT new es.safareyes.schoolevents.dtos.ParticipacionDTO(c.nombre, cu.nombre, COUNT(i))
            FROM Inscripcion i
                JOIN i.evento e
                JOIN e.categoria c
                JOIN i.usuario u
                JOIN u.curso cu
            WHERE i.asistio = true
              AND e.estado = es.safareyes.schoolevents.entities.EstadoEvento.FINALIZADO
              AND e.fechaInicio BETWEEN :desde AND :hasta
            GROUP BY c.nombre, cu.nombre
            ORDER BY c.nombre, cu.nombre
            """)
    List<ParticipacionDTO> informeParticipacion(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("""
            UPDATE Inscripcion i SET i.posicionEspera = i.posicionEspera - 1
            WHERE i.evento.id = :eventoId
              AND i.estado = es.safareyes.schoolevents.entities.EstadoInscripcion.EN_ESPERA
              AND i.posicionEspera > :posicion
            """)
    int adelantarListaEspera(@Param("eventoId") Integer eventoId, @Param("posicion") Integer posicion);
}

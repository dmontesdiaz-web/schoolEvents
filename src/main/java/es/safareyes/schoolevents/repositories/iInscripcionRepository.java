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

    @Query("""
            SELECT i FROM Inscripcion i
                JOIN FETCH i.usuario
                JOIN FETCH i.evento
            WHERE i.evento.id = :eventoId
            ORDER BY i.tipo, i.estado, i.posicionEspera, i.creadoEn
            """)
    List<Inscripcion> buscarInscritosEvento(@Param("eventoId") Integer eventoId);

    // Una inscripción por persona y por evento (RN-04)
    Optional<Inscripcion> findByUsuarioIdAndEventoId(Integer usuarioId, Integer eventoId);

    // Para las plazas ocupadas y plazas libres
    long countByEventoIdAndEstado(Integer eventoId, EstadoInscripcion estado);

    // Lista de espera
    Optional<Inscripcion> findFirstByEventoIdAndEstadoOrderByPosicionEsperaAsc(Integer eventoId,
                                                                               EstadoInscripcion estado);
    // El último de la lista de espera
    Optional<Inscripcion> findTopByEventoIdAndEstadoOrderByPosicionEsperaDesc(Integer eventoId,
                                                                              EstadoInscripcion estado);
    // Detrás de un puesto en la lista de espera
    List<Inscripcion> findByEventoIdAndEstadoAndPosicionEsperaGreaterThan(Integer eventoId,
                                                                          EstadoInscripcion estado,
                                                                          Integer posicion);

    // Solo pueden publicar los estudiantes con asistencia confirmada.
    boolean existsByUsuarioIdAndEventoIdAndAsistioTrue(Integer usuarioId, Integer eventoId);

    // Agenda que su evento, el lugar y la categoría en una única consulta
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

    // Para avisar a los inscritos de los cambios que se hayan hecho a un evento
    @Query("SELECT i.usuario FROM Inscripcion i WHERE i.evento.id = :eventoId")
    List<Usuario> findUsuariosInscritos(@Param("eventoId") Integer eventoId);

    // Informe de la participación
    @Query(nativeQuery = true, value = """
            SELECT c.nombre AS categoria, cu.nombre AS curso, COUNT(i.*) AS asistentes
            FROM inscripcion i
                JOIN evento e ON e.id = i.id_evento
                JOIN categoria c ON c.id = e.id_categoria
                JOIN usuario u ON u.id = i.id_usuario
                JOIN curso cu ON cu.id = u.id_curso
            WHERE i.asistio = true
              AND e.estado = 'FINALIZADO'
              AND e.fecha_inicio BETWEEN :desde AND :hasta
            GROUP BY c.nombre, cu.nombre
            ORDER BY c.nombre, cu.nombre
            """)
    List<ParticipacionDTO> informeParticipacion(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);}

package es.safareyes.schoolevents.dtos;

import es.safareyes.schoolevents.entities.EstadoInscripcion;
import es.safareyes.schoolevents.entities.TipoInscripcion;

import java.time.LocalDateTime;

public record InscripcionDTO(
        Integer id,
        Integer idEvento,
        String evento,
        Integer idUsuario,
        String usuario,
        TipoInscripcion tipo,
        EstadoInscripcion estado,
        Integer posicionEspera,
        Boolean asistio,
        LocalDateTime creadoEn
) {
}

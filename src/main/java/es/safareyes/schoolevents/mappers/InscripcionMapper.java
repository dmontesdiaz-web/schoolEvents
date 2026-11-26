package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.AgendaEventoDTO;
import es.safareyes.schoolevents.dtos.InscripcionDTO;
import es.safareyes.schoolevents.entities.Inscripcion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {EventoMapper.class})
public interface InscripcionMapper {

    @Mapping(source = "evento.id", target = "idEvento")
    @Mapping(source = "evento.titulo", target = "evento")
    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "usuario.nombre", target = "usuario")
    InscripcionDTO toDTO(Inscripcion inscripcion);

    List<InscripcionDTO> toDTO(List<Inscripcion> inscripcions);

    @Mapping(source = "id", target = "idInscripcion")
    AgendaEventoDTO toAgendaDTO(Inscripcion inscripcion);

    List<AgendaEventoDTO> toAgendaDTO(List<Inscripcion> inscripciones);
}

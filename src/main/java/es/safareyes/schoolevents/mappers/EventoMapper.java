package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.*;
import es.safareyes.schoolevents.entities.Evento;
import es.safareyes.schoolevents.entities.EventoCurso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {LugarMapper.class, CategoriaMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventoMapper {

    @Mapping(source = "lugar.nombre", target = "lugar")
    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "categoria.icono", target = "icono")
    @Mapping(source = "categoria.color", target = "color")
    EventoResumenDTO toResumenDTO(Evento evento);

    List<EventoResumenDTO> toResumenDTO(List<Evento> eventos);

    @Mapping(source = "lugar.nombre", target = "lugar")
    @Mapping(source = "categoria.nombre", target = "categoria")
    @Mapping(source = "categoria.icono", target = "icono")
    @Mapping(source = "categoria.color", target = "color")
    EventoCalendarioDTO toCalendarioDTO(Evento evento);

    @Mapping(source = "evento.organizador.nombre", target = "organizador")
    EventoDetalleDTO toDetalleDTO(Evento evento, Integer plazasLibres);

    @Mapping(source = "curso.id", target = "id")
    @Mapping(source = "curso.nombre", target = "nombre")
    CursoDTO toCursoDTO(EventoCurso eventoCurso);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "organizador", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    Evento toEntity(EventoCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "lugar", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    @Mapping(target = "organizador", ignore = true)
    @Mapping(target = "cursos", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "actualizadoEn", ignore = true)
    void editarDatos(@MappingTarget Evento evento, EditarEventoDTO dto);
}

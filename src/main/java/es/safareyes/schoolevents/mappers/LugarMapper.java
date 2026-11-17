package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.LugarDTO;
import es.safareyes.schoolevents.entities.Lugar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LugarMapper {
    LugarDTO toDto(Lugar lugar);
    List<LugarDTO> toDto(List<Lugar> lugares);
}

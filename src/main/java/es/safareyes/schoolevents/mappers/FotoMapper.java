package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.FotoDTO;
import es.safareyes.schoolevents.entities.FotoPublicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FotoMapper {

    FotoDTO toDTO(FotoPublicacion foto);
    List<FotoDTO> toDTO(List<FotoPublicacion> fotos);
}

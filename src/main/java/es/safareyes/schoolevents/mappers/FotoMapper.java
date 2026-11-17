package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.FotoDTO;
import es.safareyes.schoolevents.entities.FotoPublicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FotoMapper {
    @Mapping(target = "url", expression = "java(\"/api/v1/fotos/\" + foto.getId())")
    FotoDTO toDto(FotoPublicacion foto);
}

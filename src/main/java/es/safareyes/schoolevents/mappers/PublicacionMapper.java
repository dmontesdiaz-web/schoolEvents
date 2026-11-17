package es.safareyes.schoolevents.mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {FotoMapper.class})
public interface PublicacionMapper {
}

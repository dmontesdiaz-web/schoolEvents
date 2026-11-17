package es.safareyes.schoolevents.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", uses = {LugarMapper.class, CategoriaMapper.class},nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventoMapper {
    @Mapping(target = "lugar", source = "lugar.nombre")
}

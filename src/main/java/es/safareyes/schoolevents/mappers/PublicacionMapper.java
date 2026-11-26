package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.PublicacionDTO;
import es.safareyes.schoolevents.entities.Publicacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FotoMapper.class})
public interface PublicacionMapper {

    @Mapping(source = "usuario.id", target = "idAutor")
    @Mapping(source = "usuario.nombre", target = "autor")
    PublicacionDTO toDTO(Publicacion publicacion);

    List<PublicacionDTO> toDTO(List<Publicacion> publicaciones);
}

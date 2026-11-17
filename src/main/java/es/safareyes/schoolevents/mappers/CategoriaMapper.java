package es.safareyes.schoolevents.mappers;

import es.safareyes.schoolevents.dtos.CategoriaDTO;
import es.safareyes.schoolevents.entities.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    CategoriaDTO toDto(Categoria categoria);
    List<CategoriaDTO> toDto(List<Categoria> categorias);
}

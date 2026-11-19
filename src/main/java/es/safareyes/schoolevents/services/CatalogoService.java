package es.safareyes.schoolevents.services;

import es.safareyes.schoolevents.dtos.CategoriaDTO;
import es.safareyes.schoolevents.dtos.LugarDTO;
import es.safareyes.schoolevents.mappers.CategoriaMapper;
import es.safareyes.schoolevents.mappers.LugarMapper;
import es.safareyes.schoolevents.repositories.iCategoriaRepository;
import es.safareyes.schoolevents.repositories.iLugarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogoService {
    private iCategoriaRepository categoriaRepository;
    private iLugarRepository lugarRepository;
    private CategoriaMapper categoriaMapper;
    private LugarMapper lugarMapper;

    public List<CategoriaDTO> listarCategorias() {
        return categoriaMapper.toDto(categoriaRepository.findAllByOrderByNombreAsc());
    }

    public List<LugarDTO> listarLugares() {
        return lugarMapper.toDto(lugarRepository.findAllByOrderByNombreAsc());
    }
}

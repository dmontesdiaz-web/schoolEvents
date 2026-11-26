package es.safareyes.schoolevents.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record PublicacionDTO(
        Integer id,
        String codigo,
        String texto,
        Integer idAutor,
        String autor,
        LocalDateTime creadaEn,
        List<FotoDTO> fotos
) { }

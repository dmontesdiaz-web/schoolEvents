package es.safareyes.schoolevents.dtos;

public record FotoDTO(
        Integer id, String nombreOriginal,
        String tipoContenido,
        Long tamanoBytes,
        Integer orden
) { }

package es.safareyes.schoolevents.dtos;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EditarEventoDTO(
        @Size(min = 1, max = 150, message = "El título tiene que tener entre 1 y 150 caracteres")
        String titulo,
        String descripcion,
        String requisitos,
        LocalDate fechaInicio,
        LocalTime horaInicio,
        LocalDate fechaFin,
        LocalTime horaFin,
        @Positive(message = "El aforo tiene que ser mayor que 0")
        Integer aforoMaximo,
        LocalDate fechaLimite,
        Integer idLugar,
        Integer idCategoria,
        List<Integer> idCursos
) {
}

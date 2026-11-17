package es.safareyes.schoolevents.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record EventoCreateDTO(
        @NotBlank(message = "El título es obligatorio")
        @Size(max = 150, message = "El título no puede pasar de 150 caracteres")
        String titulo,

        String descripcion,
        String requisitos,
        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio,
        @NotNull(message = "La hora de inicio es obligatoria")
        LocalTime horaInicio,
        @NotNull(message = "La fecha de fin es obligatoria")
        LocalDate fechaFin,
        @NotNull(message = "La hora de fin es obligatoria")
        LocalTime horaFin,
        @Positive(message = "El aforo tiene que ser mayor que 0")
        Integer aforoMaximo,
        LocalDate fechaLimite,
        @NotNull(message = "Hay que indicar el lugar")
        Integer idLugar,
        @NotNull(message = "Hay que indicar la categoría")
        Integer idCategoria,
        List<Integer> idCursos
) {
}

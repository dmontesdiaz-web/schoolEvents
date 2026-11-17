package es.safareyes.schoolevents.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PublicacionCreateDTO(
        @NotBlank(message = "El texto es obligatorio.")
        @Size(max = 2000, message = "El texto no puede pasar de 2.000 caracteres")
        String texto
) {
}

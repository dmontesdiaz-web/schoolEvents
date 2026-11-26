package es.safareyes.schoolevents.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipacionDTO {
        String categoria;
        String curso;
        Long asistentes;
}

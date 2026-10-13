package es.safareyes.schoolevents.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformeParticipacion {
    private String categoria;
    private String curso;
    private Long recuento;
}

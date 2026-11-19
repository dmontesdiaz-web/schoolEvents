package es.safareyes.schoolevents.services;

import es.safareyes.schoolevents.dtos.ParticipacionDTO;
import es.safareyes.schoolevents.excepciones.ReglaNegocioException;
import es.safareyes.schoolevents.repositories.iInscripcionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InformeService {
    private iInscripcionRepository inscripcionRepository;
    private List<ParticipacionDTO> participacion(LocalDate desde, LocalDate hasta) {
        if (hasta.isBefore(desde)) {
            throw new ReglaNegocioException("La fecha 'hasta' no puede ser anterior a 'desde'");
        }

        return inscripcionRepository.informeParticipacion(desde, hasta);
    }

}

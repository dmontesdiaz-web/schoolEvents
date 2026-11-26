package es.safareyes.schoolevents.services;

import es.safareyes.schoolevents.dtos.PublicacionCreateDTO;
import es.safareyes.schoolevents.dtos.PublicacionDTO;
import es.safareyes.schoolevents.entities.*;
import es.safareyes.schoolevents.excepciones.AccesoDenegado;
import es.safareyes.schoolevents.excepciones.ElementoNoEncontrado;
import es.safareyes.schoolevents.excepciones.ReglaNegocioException;
import es.safareyes.schoolevents.mappers.PublicacionMapper;
import es.safareyes.schoolevents.repositories.iEventoRepository;
import es.safareyes.schoolevents.repositories.iInscripcionRepository;
import es.safareyes.schoolevents.repositories.iPublicacionRepository;
import es.safareyes.schoolevents.repositories.iUsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PublicacionService {

    private iPublicacionRepository repository;
    private iEventoRepository eventoRepository;
    private iUsuarioRepository usuarioRepository;
    private iInscripcionRepository inscripcionRepository;
    private NotificacionService notificacionService;
    private PublicacionMapper publicacionMapper;

    @Transactional
    public PublicacionDTO publicar(Integer idEvento, PublicacionCreateDTO dto, Integer idUsuario) {

        Evento evento = buscarEventoVisible(idEvento);

        Usuario autor = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ElementoNoEncontrado("No existe el usuario con id " + idUsuario));

        if (evento.getEstado() != EstadoEvento.FINALIZADO) {
            throw new ReglaNegocioException("Solo se publican resultados de eventos finalizados");
        }

        boolean esOrganizador = autor.getRol() == RolUsuario.ORGANIZADOR || evento.getOrganizador().getId().equals(autor.getId());
        boolean asistio = inscripcionRepository.existsByUsuarioIdAndEventoIdAndAsistioTrue(idUsuario, idEvento);
        if (!esOrganizador && !asistio) {
            throw new AccesoDenegado("Solo publican el organizador y los estudiantes que asistieron");
        }

        Publicacion publicacion = new Publicacion();
        publicacion.setCodigo("TMP-" + UUID.randomUUID().toString().substring(0, 13));
        publicacion.setTexto(dto.texto().trim());
        publicacion.setUsuario(autor);
        publicacion.setEvento(evento);
        repository.save(publicacion);
        publicacion.setCodigo(String.format("PUB-%03d", publicacion.getId()));
        repository.save(publicacion);
        log.info("Publicación {} creada en el evento {} por el usuario {}", publicacion.getCodigo(), idEvento, idUsuario);

        List<Usuario> seguidores = new ArrayList<>();
        for (Usuario u : inscripcionRepository.findUsuariosInscritos(idEvento)) {
            if (!u.getId().equals(idUsuario)) {
                seguidores.add(u);
            }
        }

        notificacionService.notificar(TipoNotificacion.NUEVA_PUBLICACION, "Hay resultados nuevos de \"" + evento.getTitulo() + "\".", TipoRecurso.PUBLICACION, publicacion.getId(), seguidores);
        return publicacionMapper.toDTO(publicacion);
    }

    public List<PublicacionDTO> listar(Integer idEvento) {
        // Esto es para el endpoint 15, get de publicaciones con sus fotos.
        buscarEventoVisible(idEvento);
        return publicacionMapper.toDTO(repository.buscarPorEventoConFotos(idEvento));
    }

    private Evento buscarEventoVisible(Integer idEvento) {
        Evento evento = eventoRepository.findById(idEvento)
                .orElseThrow(() -> new ElementoNoEncontrado("No existe el evento con id " + idEvento));

        // Asegurarnos que los borradores no son públicos
        if (evento.getEstado() == EstadoEvento.BORRADOR) {
            throw new ElementoNoEncontrado("No existe el evento con id " + idEvento);
        }

        return evento;
    }
}

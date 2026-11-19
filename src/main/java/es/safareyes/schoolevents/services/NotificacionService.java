package es.safareyes.schoolevents.services;

import es.safareyes.schoolevents.entities.*;
import es.safareyes.schoolevents.repositories.iNotificacionRepository;
import es.safareyes.schoolevents.repositories.iUsuarioNotificacionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class NotificacionService {
    private iNotificacionRepository repository;
    private iUsuarioNotificacionRepository usuarioNotificacionRepository;

    @Transactional
    public void notificar(TipoNotificacion tipo, String mensaje, TipoRecurso recurso, Integer recursoId, List<Usuario> destinatarios) {
        List<Usuario> unicos = new ArrayList<>();
        List<Integer> ids= new ArrayList<>();
        for (Usuario u : destinatarios) {
            if (!ids.contains(u.getId())) {
                ids.add(u.getId());
                unicos.add(u);
            }
        }

        if (unicos.isEmpty()) { return; }

        Notificacion notificacion = new Notificacion();
        notificacion.setCodigo("TMP-" + UUID.randomUUID().toString().substring(0, 13));
        notificacion.setTipo(tipo);
        notificacion.setMensaje(mensaje);
        notificacion.setRecurso(recurso);
        notificacion.setRecursoId(recursoId);
        repository.save(notificacion);
        notificacion.setCodigo(String.format("NOT-%03d", notificacion.getId()));
        repository.save(notificacion);

        for (Usuario usuario : unicos) {
            UsuarioNotificacion fila = new UsuarioNotificacion();
            fila.setUsuario(usuario);
            fila.setNotificacion(notificacion);
            fila.setLeida(false);
            usuarioNotificacionRepository.save(fila);
        }
        log.info("Notificación {} {} enviada a {} usuarios", notificacion.getCodigo(), tipo, unicos.size());
    }
}

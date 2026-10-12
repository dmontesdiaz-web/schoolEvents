package es.safareyes.schoolevents.repositorios;

import es.safareyes.schoolevents.modelos.UsuarioNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUsuarioNotificacionRepository extends JpaRepository<UsuarioNotificacion, Integer> {
}

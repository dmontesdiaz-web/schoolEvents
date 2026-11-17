package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.UsuarioNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUsuarioNotificacionRepository extends JpaRepository<UsuarioNotificacion, Integer> {
}

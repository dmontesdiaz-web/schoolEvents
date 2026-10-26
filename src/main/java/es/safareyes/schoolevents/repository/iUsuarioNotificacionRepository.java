package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.UsuarioNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUsuarioNotificacionRepository extends JpaRepository<UsuarioNotificacion, Integer> {
}

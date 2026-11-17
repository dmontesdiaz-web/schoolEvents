package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iNotificacionRepository extends JpaRepository<Notificacion, Integer> {
}

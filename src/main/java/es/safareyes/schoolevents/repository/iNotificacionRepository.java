package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iNotificacionRepository extends JpaRepository<Notificacion, Integer> {
}

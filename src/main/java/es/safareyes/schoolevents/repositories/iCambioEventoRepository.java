package es.safareyes.schoolevents.repositories;

import es.safareyes.schoolevents.entities.CambioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCambioEventoRepository extends JpaRepository<CambioEvento, Integer> {
}

package es.safareyes.schoolevents.repository;

import es.safareyes.schoolevents.entity.CambioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCambioEventoRepository extends JpaRepository<CambioEvento, Integer> {
}

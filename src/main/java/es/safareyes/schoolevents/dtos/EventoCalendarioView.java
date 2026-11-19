package es.safareyes.schoolevents.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public interface EventoCalendarioView {
    Integer getId();

    String getTitulo();

    LocalDate getDia();

    LocalTime getHoraInicio();

    LocalTime getHoraFin();

    String getLugar();

    String getCategoria();

    String getIcono();

    String getColor();
}

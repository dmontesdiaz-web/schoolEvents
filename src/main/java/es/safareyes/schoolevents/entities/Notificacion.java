package es.safareyes.schoolevents.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacion")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false, length = 20, unique = true)
    private String codigo;

    @Column(name = "tipo", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TipoNotificacion tipo;

    @Column(name = "mensaje", nullable = false, length = 500)
    private String mensaje;

    @Column(name = "recurso", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoRecurso recurso;

    @Column(name = "recurso_id", nullable = false)
    private Integer recursoId;

    @CreatedDate
    @Column(name = "creada_en", nullable = false)
    private LocalDateTime creadaEn;
}

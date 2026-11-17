package es.safareyes.schoolevents.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscripcion", uniqueConstraints = {
        @UniqueConstraint(name = "ux_inscripcion", columnNames = {"id_usuario", "id_evento"})
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;

    @Column(name = "tipo", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TipoInscripcion tipo;

    @Column(name = "estado", length = 20)
    @Enumerated(EnumType.STRING)
    private EstadoInscripcion estado;

    @Column(name = "asistio")
    private Boolean asistio;

    @Column(name = "posicion_espera")
    private Integer posicionEspera;

    @Column(name = "recordatorio_24h_enviado", nullable = false)
    private boolean recordatorio24hEnviado;

    @Column(name = "recordatorio_1h_enviado", nullable = false)
    private boolean recordatorio1hEnviado;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime creadoEn;

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime actualizadoEn;

    @PrePersist
    void alCrear() {
        creadoEn = LocalDateTime.now();
        actualizadoEn = creadoEn;
    }

    @PreUpdate
    void alActualizar() {
        actualizadoEn = LocalDateTime.now();
    }
}
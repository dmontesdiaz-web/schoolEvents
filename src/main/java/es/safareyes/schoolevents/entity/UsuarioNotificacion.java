
package es.safareyes.schoolevents.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_notificacion", uniqueConstraints = {
        @UniqueConstraint(name = "ux_usuario_notificacion", columnNames = {"id_usuario", "id_notificacion"})
})
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioNotificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_notificacion", nullable = false)
    private Notificacion notificacion;

    @Column(name = "leida", nullable = false)
    private boolean leida;

    // null mientras no se lea
    @Column(name = "leida_en")
    private LocalDateTime leidaEn;
}
 
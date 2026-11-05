package es.safareyes.schoolevents.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "foto_publicacion")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FotoPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", nullable = false, length = 20, unique = true)
    private String codigo;

    @Column(name = "nombre_original", nullable = false, length = 255)
    private String nombreOriginal;

    // UUID + extension
    @Column(name = "clave", nullable = false, length = 100, unique = true)
    private String clave;

    @Column(name = "tipo_contenido", nullable = false, length = 50)
    private String tipoContenido;

    @Column(name = "tamano_bytes", nullable = false)
    private Long tamanoBytes;

    @Column(name = "orden", nullable = false)
    private Integer orden;

    @Column(name = "subida_en", nullable = false)
    private LocalDateTime subidaEn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_publicacion", nullable = false)
    private Publicacion publicacion;

    @PrePersist
    void alCrear() {
        subidaEn = LocalDateTime.now();
    }
}

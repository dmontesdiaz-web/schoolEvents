package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "biotopo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Biotopo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_continente")
    private Continente continente;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "especie_biotopo",
            joinColumns = @JoinColumn(name = "id_biotopo"),
            inverseJoinColumns = @JoinColumn(name = "id_especie")
    )
    private List<Especie> especie;

}

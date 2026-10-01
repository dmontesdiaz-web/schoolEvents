package es.safareyes.schoolevents.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cat_continente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Continente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @Column(name = "codigo")
    private String codigo;

}

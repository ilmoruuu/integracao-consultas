package upe.br.consultas.infra.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recepicionista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
}

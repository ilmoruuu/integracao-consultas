package upe.br.consultas.infra.entities;

import jakarta.persistence.*;
import lombok.*;
import upe.br.consultas.infra.enums.EspecialidadesEnum;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String telefone;
    private String email;

    @Enumerated(EnumType.STRING)
    private EspecialidadesEnum especializacao;
    private String crm;

}

package upe.br.consultas.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import upe.br.consultas.infra.enums.SexoEnum;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer nome;
    private int idade;
    private String cpf;
    private String email;
    private String telefone;
    private ArrayList<Byte> historico = new ArrayList<>(); //Histórico será vários documentos médicos
    private SexoEnum sexo;
    private ArrayList<String> restricoes = new ArrayList<>(); //Alergias, comprometimentos

}

package upe.br.consultas.controller.DTO.paciente;

import org.hibernate.annotations.processing.Pattern;
import upe.br.consultas.infra.enums.SexoEnum;

public record PacienteCriadoDTO(
        String nome,
        int idade,
        String cpf,
        String email,
        String telefone,
        SexoEnum sexo
) {

}

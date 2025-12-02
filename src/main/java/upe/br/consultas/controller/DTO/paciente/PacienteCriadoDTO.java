package upe.br.consultas.controller.DTO.paciente;

import org.hibernate.annotations.processing.Pattern;
import upe.br.consultas.infra.enums.SexoEnum;

import java.util.ArrayList;

public record PacienteCriadoDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        ArrayList<Byte> historico,
        SexoEnum sexo,
        ArrayList<String> restricoes,
        int idade
) {}


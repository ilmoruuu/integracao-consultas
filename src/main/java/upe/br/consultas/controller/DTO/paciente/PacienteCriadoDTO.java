package upe.br.consultas.controller.DTO.paciente;

import upe.br.consultas.infra.enums.SexoEnum;

import java.util.List;

public record PacienteCriadoDTO(
        String nome,
        String cpf,
        String email,
        String telefone,
        List<Byte> historico,
        SexoEnum sexo,
        List<String> restricoes,
        int idade
) {}


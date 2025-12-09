package upe.br.consultas.controller.DTO.recepcionista;

import upe.br.consultas.infra.enums.SexoEnum;

import java.util.List;

public record RecepcionistaCriadoDTO(

        String nome,
        String telefone,
        String email

) {}


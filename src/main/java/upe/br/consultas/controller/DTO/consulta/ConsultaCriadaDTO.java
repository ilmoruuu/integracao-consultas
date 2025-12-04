package upe.br.consultas.controller.DTO.consulta;

import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

import java.time.LocalDate;
import java.util.List;

public record ConsultaCriadaDTO(
        LocalDate data,
        String descricao,
        Integer medicoId,
        Integer pacienteId,
        Integer recepcionistaId,
        List<String> materiaisRequisitados,
        Double valor,
        CategoriaProdutoEnum categoria,
        Integer quantidadeMaterial
) {}
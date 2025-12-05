package upe.br.consultas.controller.DTO.notificacoes;

import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

import java.util.List;

public record MsgEstoqueDTO(
        Integer id,
        List<String> materiaisRequisitados,
        CategoriaProdutoEnum categoria,
        Integer quantidadeMaterial
) {
}

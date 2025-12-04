package upe.br.consultas.controller.DTO.notificacoes;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public record MsgFinanceiroDTO (
    Integer idConsulta,
    Integer idMedico,
    Double valorConsulta
) {}
package upe.br.consultas.controller.DTO.notificacoes;

import java.time.LocalDate;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public record MsgFinanceiroDTO (
    Integer id,
    Integer idMedico,
    Double valorConsulta,
    LocalDate dataConsulta
) {}
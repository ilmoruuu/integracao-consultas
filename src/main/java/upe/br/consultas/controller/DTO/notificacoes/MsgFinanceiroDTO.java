package upe.br.consultas.controller.DTO.notificacoes;

import java.time.LocalDate;

public record MsgFinanceiroDTO (
    Integer id,
    Integer idMedico,
    Double valorConsulta,
    LocalDate dataConsulta
) {}
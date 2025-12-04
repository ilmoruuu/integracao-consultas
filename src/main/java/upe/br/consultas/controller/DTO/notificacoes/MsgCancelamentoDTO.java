package upe.br.consultas.controller.DTO.notificacoes;

import java.time.LocalDate;

// Esse é o que vai ser enviado via RabbitMQ quando uma consulta for cancelada
public record MsgCancelamentoDTO(
    String emailPaciente,
    String nomePaciente,
    String nomeMedico,
    LocalDate dataConsulta,
    String descricao
) {}
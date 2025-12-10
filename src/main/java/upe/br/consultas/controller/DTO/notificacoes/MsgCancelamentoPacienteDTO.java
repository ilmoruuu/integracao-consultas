package upe.br.consultas.controller.DTO.notificacoes;

import java.time.LocalDate;

// Esse é o que vai ser enviado via RabbitMQ quando uma consulta for cancelada
public record MsgCancelamentoPacienteDTO(
    String emailPaciente,
    String nomePaciente,
    String nomeMedico,
    String especialidadeMedico,
    LocalDate dataConsulta,
    String descricao
) {}
package upe.br.consultas.controller.DTO.notificacoes;

import java.time.LocalDate;

public record MsgCancelamentoMedicoDTO(
        String emailMedico,
        String nomeMedico,
        String nomePaciente,
        LocalDate dataConsulta,
        String descricao
) {}
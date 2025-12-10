package upe.br.consultas.controller.DTO.notificacoes;

public record MsgCancelamentoDTO(
        String emailPaciente,
        String nomePaciente,
        String nomeMedico,
        String especialidadeMedico,
        String dataConsulta,
        String descricao
) {
}

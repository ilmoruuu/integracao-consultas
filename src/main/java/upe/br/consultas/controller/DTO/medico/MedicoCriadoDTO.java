package upe.br.consultas.controller.DTO.medico;

import upe.br.consultas.infra.enums.EspecialidadesEnum;

public record MedicoCriadoDTO(
        String nome,
        String telefone,
        String email,
        EspecialidadesEnum especializacao,
        String crm
) {
}

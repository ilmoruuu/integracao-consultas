package upe.br.consultas.controller.DTO.medico;

import upe.br.consultas.infra.entities.Medico;

public record MedicoResumoDTO(Integer id, String nome, String crm) {
    public static MedicoResumoDTO fromEntity(Medico medico) {
        return new MedicoResumoDTO(medico.getId(), medico.getNome(), medico.getCrm());
    }
}
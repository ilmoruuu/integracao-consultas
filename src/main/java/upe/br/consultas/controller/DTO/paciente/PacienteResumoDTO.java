package upe.br.consultas.controller.DTO.paciente;

import upe.br.consultas.infra.entities.Paciente;

public record PacienteResumoDTO(Integer id, String nome, String cpf) {
    public static PacienteResumoDTO fromEntity(Paciente paciente) {
        return new PacienteResumoDTO(paciente.getId(), paciente.getNome(), paciente.getCpf());
    }
}
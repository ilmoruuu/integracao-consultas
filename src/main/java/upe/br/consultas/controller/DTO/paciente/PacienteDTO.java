package upe.br.consultas.controller.DTO.paciente;

import upe.br.consultas.infra.entities.Paciente;
import upe.br.consultas.infra.enums.SexoEnum;

import java.util.List;

public record PacienteDTO(
        Integer id,
        String nome,
        String cpf,
        String email,
        String telefone,
        List<Byte> historico,
        SexoEnum sexo,
        List<String> restricoes
) {
    public static PacienteDTO pacienteToDTO (Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getHistorico(),
                paciente.getSexo(),
                paciente.getRestricoes()
        );
    }
}

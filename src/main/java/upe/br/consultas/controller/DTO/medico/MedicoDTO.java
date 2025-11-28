package upe.br.consultas.controller.DTO.medico;

import upe.br.consultas.infra.entities.Medico;
import upe.br.consultas.infra.enums.EspecialidadesEnum;

public record MedicoDTO (
        Integer id,
        String nome,
        String telefone,
        String email,
        EspecialidadesEnum especializacao,
        String crm
) {
    public static MedicoDTO medicotoDTO(Medico medico) {
        return new MedicoDTO(
                medico.getId(),
                medico.getNome(),
                medico.getTelefone(),
                medico.getEmail(),
                medico.getEspecializacao(),
                medico.getCrm()
        );
    }
}

package upe.br.consultas.controller.DTO.consulta;

import upe.br.consultas.controller.DTO.medico.MedicoResumoDTO;
import upe.br.consultas.controller.DTO.paciente.PacienteResumoDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaResumoDTO;
import upe.br.consultas.infra.entities.Consulta;
import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

import java.time.LocalDate;
import java.util.List;

public record ConsultaDTO(
        Integer id,
        LocalDate data,
        String descricao,
        MedicoResumoDTO medico,
        PacienteResumoDTO paciente,
        RecepcionistaResumoDTO recepcionista,
        List<String> materiaisRequisitados,
        Double valor,
        CategoriaProdutoEnum categoria,
        Integer quantidadeMaterial
) {

    public static ConsultaDTO consultaToDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getId(),
                consulta.getData(),
                consulta.getDescricao(),
                MedicoResumoDTO.fromEntity(consulta.getMedico()),
                PacienteResumoDTO.fromEntity(consulta.getPaciente()),
                RecepcionistaResumoDTO.fromEntity(consulta.getRecepcionista()),
                consulta.getMateriaisRequisitados(),
                consulta.getValor(),
                consulta.getCategoria(),
                consulta.getQuantidadeMaterial()
        );
    }
}

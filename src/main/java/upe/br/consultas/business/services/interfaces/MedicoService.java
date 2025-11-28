package upe.br.consultas.business.services.interfaces;

import upe.br.consultas.controller.DTO.medico.MedicoDTO;
import upe.br.consultas.infra.enums.EspecialidadesEnum;

import java.util.List;

public interface MedicoService {
    MedicoDTO cadastrarMedico(MedicoDTO medicoDTO);
    MedicoDTO atualizarMedico(MedicoDTO medicoDTO);
    void excluirMedico(MedicoDTO medicoDTO);
    List<MedicoDTO> listarMedicos();
    MedicoDTO buscarMedicoPorId(Long id);
    MedicoDTO buscarMedicoPorNome(String nome);
    MedicoDTO buscarMedicoPorEmail(String email);
    MedicoDTO buscarMedicoPorEspecializacao(EspecialidadesEnum especializacao);
    MedicoDTO buscarMedicoPorCrm(String crm);
}

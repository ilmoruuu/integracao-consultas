package upe.br.consultas.business.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upe.br.consultas.business.services.interfaces.MedicoService;
import upe.br.consultas.controller.DTO.medico.MedicoDTO;
import upe.br.consultas.infra.enums.EspecialidadesEnum;
import upe.br.consultas.infra.repositories.MedicoRepository;

import java.util.List;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public MedicoDTO cadastrarMedico(MedicoDTO medicoDTO) { return null; }

    @Override
    public MedicoDTO atualizarMedico(MedicoDTO medicoDTO) {
        return null;
    }

    @Override
    public void excluirMedico(MedicoDTO medicoDTO) {

    }

    @Override
    public List<MedicoDTO> listarMedicos() {
        return List.of();
    }

    @Override
    public MedicoDTO buscarMedicoPorId(Long id) {
        return null;
    }

    @Override
    public MedicoDTO buscarMedicoPorNome(String nome) {
        return null;
    }

    @Override
    public MedicoDTO buscarMedicoPorEmail(String email) {
        return null;
    }

    @Override
    public MedicoDTO buscarMedicoPorEspecializacao(EspecialidadesEnum especializacao) {
        return null;
    }

    @Override
    public MedicoDTO buscarMedicoPorCrm(String crm) {
        return null;
    }


}

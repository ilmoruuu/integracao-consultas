package upe.br.consultas.business.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upe.br.consultas.business.services.interfaces.MedicoService;
import upe.br.consultas.controller.DTO.medico.MedicoCriadoDTO;
import upe.br.consultas.controller.DTO.medico.MedicoDTO;
import upe.br.consultas.infra.entities.Medico;
import upe.br.consultas.infra.enums.EspecialidadesEnum;
import upe.br.consultas.infra.repositories.MedicoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public MedicoDTO cadastrarMedico(MedicoCriadoDTO medicoCriadoDTO) {

        Medico medico = new Medico(
                medicoCriadoDTO.nome(),
                medicoCriadoDTO.telefone(),
                medicoCriadoDTO.email(),
                medicoCriadoDTO.especializacao(),
                medicoCriadoDTO.crm()
        );

        return MedicoDTO.medicotoDTO(medicoRepository.save(medico));
    }

    @Override
    public MedicoDTO atualizarMedico(MedicoDTO medicoDTO) {

        Medico medico = medicoRepository.findById(medicoDTO.id())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        medico.setNome(medicoDTO.nome());
        medico.setTelefone(medicoDTO.telefone());
        medico.setEmail(medicoDTO.email());
        medico.setEspecializacao(medicoDTO.especializacao());
        medico.setCrm(medicoDTO.crm());

        return MedicoDTO.medicotoDTO(medicoRepository.save(medico));
    }

    @Override
    public void excluirMedico(Integer id) {
        if (!medicoRepository.existsById(id)) {
            throw new RuntimeException("Médico não encontrado");
        }
        medicoRepository.deleteById(id);
    }

    @Override
    public List<MedicoDTO> listarMedicos() {
        return medicoRepository.findAll()
                .stream()
                .map(MedicoDTO::medicotoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDTO buscarMedicoPorId(Integer id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return MedicoDTO.medicotoDTO(medico);
    }

    @Override
    public MedicoDTO buscarMedicoPorNome(String nome) {
        Medico medico = medicoRepository.findMedicoByNome(nome)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return MedicoDTO.medicotoDTO(medico);
    }

    @Override
    public MedicoDTO buscarMedicoPorEmail(String email) {
        Medico medico = medicoRepository.findMedicoByEmail(email)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return MedicoDTO.medicotoDTO(medico);
    }

    @Override
    public MedicoDTO buscarMedicoPorEspecializacao(EspecialidadesEnum especializacao) {
        Medico medico = medicoRepository.findMedicoByEspecializacao(especializacao)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return MedicoDTO.medicotoDTO(medico);
    }

    @Override
    public MedicoDTO buscarMedicoPorCrm(String crm) {
        Medico medico = medicoRepository.findMedicoByCrm(crm)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        return MedicoDTO.medicotoDTO(medico);
    }

}

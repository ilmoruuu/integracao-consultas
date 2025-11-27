package upe.br.consultas.business.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upe.br.consultas.business.services.interfaces.PacienteService;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;
import upe.br.consultas.infra.repositories.PacienteRepository;

import java.util.List;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public PacienteDTO cadastrarPaciente(PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public PacienteDTO atualizarPaciente(PacienteDTO pacienteDTO) {
        return null;
    }

    @Override
    public void excluirPaciente(PacienteDTO pacienteDTO) {

    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        return List.of();
    }

    @Override
    public PacienteDTO buscarPacientePorId(Integer id) {
        return null;
    }

    @Override
    public PacienteDTO buscarPacientePorNome(String nome) {
        return null;
    }

    @Override
    public PacienteDTO buscarPacientePorCpf(String cpf) {
        return null;
    }
}

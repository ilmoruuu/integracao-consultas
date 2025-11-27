package upe.br.consultas.business.services.interfaces;

import org.springframework.stereotype.Service;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;

import java.util.List;

@Service
public interface PacienteService {
    PacienteDTO cadastrarPaciente(PacienteDTO pacienteDTO);
    PacienteDTO atualizarPaciente(PacienteDTO pacienteDTO);
    void excluirPaciente(PacienteDTO pacienteDTO);
    List<PacienteDTO> listarPacientes();
    PacienteDTO buscarPacientePorId(Integer id);
    PacienteDTO buscarPacientePorNome(String nome);
    PacienteDTO buscarPacientePorCpf(String cpf);
}

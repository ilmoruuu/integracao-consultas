package upe.br.consultas.business.services.interfaces;

import upe.br.consultas.controller.DTO.paciente.PacienteCriadoDTO;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;

import java.util.List;

public interface PacienteService {
    PacienteDTO cadastrarPaciente(PacienteCriadoDTO pacienteCriadoDTO);
    PacienteDTO atualizarPaciente(PacienteDTO pacienteDTO);
    void excluirPaciente(Integer id);
    List<PacienteDTO> listarPacientes();
    PacienteDTO buscarPacientePorId(Integer id);
    PacienteDTO buscarPacientePorNome(String nome);
    PacienteDTO buscarPacientePorCpf(String cpf);
}


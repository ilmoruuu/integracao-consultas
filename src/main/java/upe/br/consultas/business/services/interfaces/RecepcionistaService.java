package upe.br.consultas.business.services.interfaces;

import upe.br.consultas.controller.DTO.paciente.PacienteCriadoDTO;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaResumoDTO;

import java.util.List;

public interface RecepcionistaService {

    RecepcionistaResumoDTO cadastrarRecepcionista(PacienteCriadoDTO pacienteCriadoDTO);
    RecepcionistaResumoDTO atualizarPaciente(PacienteDTO pacienteDTO);
    List<PacienteDTO> listarPacientes();
    RecepcionistaResumoDTO buscarPacientePorId(Integer id);
    List<RecepcionistaResumoDTO> buscarRecepcionistaPorNome(String nome);
    RecepcionistaResumoDTO buscarRecepcionistaPorTelefone(String cpf);
}

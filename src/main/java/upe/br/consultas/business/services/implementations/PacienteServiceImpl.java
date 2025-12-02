package upe.br.consultas.business.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upe.br.consultas.business.services.interfaces.PacienteService;
import upe.br.consultas.controller.DTO.paciente.PacienteCriadoDTO;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;
import upe.br.consultas.infra.entities.Paciente;
import upe.br.consultas.infra.exceptions.PacienteExistenteException;
import upe.br.consultas.infra.exceptions.PacienteNaoEncontradoException;
import upe.br.consultas.infra.repositories.PacienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public PacienteDTO cadastrarPaciente(PacienteCriadoDTO pacienteCriadoDTO) {

        Optional<Paciente> pacienteBanco = pacienteRepository.findByCpf(pacienteCriadoDTO.cpf());

        if (pacienteBanco.isPresent()){
            throw new PacienteExistenteException();
        }

        Paciente paciente = Paciente.builder()
                .nome(pacienteCriadoDTO.nome())
                .cpf(pacienteCriadoDTO.cpf())
                .email(pacienteCriadoDTO.email())
                .telefone(pacienteCriadoDTO.telefone())
                .idade(pacienteCriadoDTO.idade())
                .sexo(pacienteCriadoDTO.sexo())
                .historico(pacienteCriadoDTO.historico() == null ? new ArrayList<>() : pacienteCriadoDTO.historico())
                .restricoes(pacienteCriadoDTO.restricoes() == null ? new ArrayList<>() : pacienteCriadoDTO.restricoes())
                .build();

        Paciente salvo = pacienteRepository.save(paciente);

        return PacienteDTO.pacienteToDTO(salvo);
    }

    @Override
    public PacienteDTO atualizarPaciente(PacienteDTO pacienteDTO) {

        Paciente paciente = pacienteRepository.findById(pacienteDTO.id())
                .orElseThrow(PacienteNaoEncontradoException::new);

        paciente.setNome(pacienteDTO.nome());
        paciente.setCpf(pacienteDTO.cpf());
        paciente.setEmail(pacienteDTO.email());
        paciente.setTelefone(pacienteDTO.telefone());
        paciente.setSexo(pacienteDTO.sexo());
        paciente.setHistorico(pacienteDTO.historico());
        paciente.setRestricoes(pacienteDTO.restricoes());

        Paciente atualizado = pacienteRepository.save(paciente);

        return PacienteDTO.pacienteToDTO(atualizado);
    }

    @Override
    public void excluirPaciente(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(PacienteNaoEncontradoException::new);

        pacienteRepository.delete(paciente);
    }

    @Override
    public List<PacienteDTO> listarPacientes() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteDTO::pacienteToDTO)
                .toList();
    }

    @Override
    public PacienteDTO buscarPacientePorId(Integer id) {
        return pacienteRepository.findById(id)
                .map(PacienteDTO::pacienteToDTO)
                .orElseThrow(PacienteNaoEncontradoException::new);
    }

    @Override
    public PacienteDTO buscarPacientePorNome(String nome) {
        return pacienteRepository.findByNome(nome)
                .map(PacienteDTO::pacienteToDTO)
                .orElseThrow(PacienteNaoEncontradoException::new);
    }

    @Override
    public PacienteDTO buscarPacientePorCpf(String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .map(PacienteDTO::pacienteToDTO)
                .orElseThrow(PacienteNaoEncontradoException::new);
    }
}

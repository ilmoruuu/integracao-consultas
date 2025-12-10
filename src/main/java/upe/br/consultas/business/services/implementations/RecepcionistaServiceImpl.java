package upe.br.consultas.business.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upe.br.consultas.business.services.interfaces.RecepcionistaService;
import upe.br.consultas.controller.DTO.recepcionista.LoginDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaCriadaDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;
import upe.br.consultas.infra.entities.Recepcionista;
import upe.br.consultas.infra.repositories.RecepcionistaRepository;

import java.util.List;

@Service
@Transactional
public class RecepcionistaServiceImpl implements RecepcionistaService {

    @Autowired
    private RecepcionistaRepository repository;

    @Override
    public RecepcionistaDTO cadastrarRecepcionista(RecepcionistaCriadaDTO dto) {
        Recepcionista recepcionista = Recepcionista.builder()
                .nome(dto.nome())
                .telefone(dto.telefone())
                .email(dto.email())
                .senha(dto.senha())
                .build();

        Recepcionista salvo = repository.save(recepcionista);
        return RecepcionistaDTO.recepcionistaToDTO(salvo);
    }

    @Override
    public RecepcionistaDTO atualizarRecepcionista(RecepcionistaDTO dto) {
        Recepcionista recepcionista = repository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrada"));

        recepcionista.setNome(dto.nome());
        recepcionista.setTelefone(dto.telefone());
        recepcionista.setEmail(dto.email());

        Recepcionista atualizado = repository.save(recepcionista);
        return RecepcionistaDTO.recepcionistaToDTO(atualizado);
    }

    @Override
    public void excluirRecepcionista(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Recepcionista não encontrada");
        }
        repository.deleteById(id);
    }

    @Override
    public List<RecepcionistaDTO> listarRecepcionistas() {
        return repository.findAll().stream()
                .map(RecepcionistaDTO::recepcionistaToDTO)
                .toList();
    }

    @Override
    public RecepcionistaDTO buscarPorId(Integer id) {
        return repository.findById(id)
                .map(RecepcionistaDTO::recepcionistaToDTO)
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrada"));
    }

    @Override
    public List<RecepcionistaDTO> buscarPorNome(String nome) {
        // Assume-se que você adicionou findByNomeContainingIgnoreCase no repository igual fizemos no médico
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(RecepcionistaDTO::recepcionistaToDTO)
                .toList();
    }

    @Override
    public RecepcionistaDTO logar(LoginDTO login) {

        Recepcionista recepcionista = repository.findByEmail(login.email())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha inválidos"));
        if (!recepcionista.getSenha().equals(login.senha())) {
            throw new RuntimeException("E-mail ou senha inválidos");
        }
        return RecepcionistaDTO.recepcionistaToDTO(recepcionista);
    }
}
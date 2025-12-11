package upe.br.consultas.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import upe.br.consultas.business.services.interfaces.PacienteService;
import upe.br.consultas.controller.DTO.paciente.PacienteCriadoDTO;
import upe.br.consultas.controller.DTO.paciente.PacienteDTO;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody PacienteCriadoDTO dto) {
        PacienteDTO criado = pacienteService.cadastrarPaciente(dto);
        System.out.println("Cadastrado com sucesso");
        return ResponseEntity.ok(criado);
    }

    @PutMapping
    public ResponseEntity<PacienteDTO> atualizar(@RequestBody PacienteDTO dto) {
        PacienteDTO atualizado = pacienteService.atualizarPaciente(dto);
        System.out.println("Atualizado com sucesso");
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        pacienteService.excluirPaciente(id);
        System.out.println("Excluido com sucesso");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar() {
        return ResponseEntity.ok(pacienteService.listarPacientes());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PacienteDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PacienteDTO>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorNome(nome));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteDTO> buscarPorCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(pacienteService.buscarPacientePorCpf(cpf));
    }
}

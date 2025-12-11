package upe.br.consultas.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upe.br.consultas.business.services.interfaces.ConsultaService;

import upe.br.consultas.controller.DTO.consulta.ConsultaCriadaDTO;
import upe.br.consultas.controller.DTO.consulta.ConsultaDTO;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> agendar(@RequestBody ConsultaCriadaDTO dto) {
        ConsultaDTO agendada = consultaService.agendarConsulta(dto);
        System.out.println("Consulta agendada com sucesso");
        return ResponseEntity.ok(agendada);
    }

    @PutMapping
    public ResponseEntity<ConsultaDTO> atualizar(@RequestBody ConsultaDTO dto) {
        ConsultaDTO atualizada = consultaService.atualizarConsulta(dto);
        System.out.println("Consulta atualizada com sucesso");
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desmarcar(@PathVariable Integer id) {
        consultaService.desmarcarConsulta(id);
        System.out.println("Consulta desmarcada com sucesso");
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(consultaService.buscarConsultaPorId(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorPaciente(pacienteId));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorMedico(@PathVariable Integer medicoId) {
        return ResponseEntity.ok(consultaService.buscarConsultasPorMedico(medicoId));
    }

    @GetMapping("/paciente/nome/{nome}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorNomePaciente(@PathVariable String nome) {
        List<ConsultaDTO> resultado = consultaService.buscarConsultasPorNomePaciente(nome);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/medico/nome/{nome}")
    public ResponseEntity<List<ConsultaDTO>> buscarPorNomeMedico(@PathVariable String nome) {
        List<ConsultaDTO> resultado = consultaService.buscarConsultasPorNomeMedico(nome);
        return ResponseEntity.ok(resultado);
    }
}
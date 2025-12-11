package upe.br.consultas.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upe.br.consultas.business.services.interfaces.MedicoService;
import upe.br.consultas.controller.DTO.medico.MedicoCriadoDTO;
import upe.br.consultas.controller.DTO.medico.MedicoDTO;
import upe.br.consultas.infra.enums.EspecialidadesEnum;

@RestController
@CrossOrigin("*")
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<MedicoDTO> cadastrarMedico(@RequestBody MedicoCriadoDTO medicoCriadoDTO) {
        MedicoDTO criado = medicoService.cadastrarMedico(medicoCriadoDTO);
        System.out.println("Médico Criado: " + criado);
        return ResponseEntity.ok(criado);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<MedicoDTO> atualizarMedico(@RequestBody MedicoDTO medicoDTO) {
        return ResponseEntity
                .ok(medicoService.atualizarMedico(medicoDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(medicoService.buscarMedicoPorId(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<MedicoDTO> getMedicoByNome(@RequestParam String nome) {
        return ResponseEntity.ok().body(medicoService.buscarMedicoPorNome(nome));
    }

    @GetMapping("/especializacao")
    public ResponseEntity<MedicoDTO> getMedicoByEspecializacao(@RequestParam EspecialidadesEnum especializacao) {
        return ResponseEntity.ok().body(medicoService.buscarMedicoPorEspecializacao(especializacao));
    }

    @GetMapping("/email")
    public ResponseEntity<MedicoDTO> getMedicoByEmail(@RequestParam String email) {
        return ResponseEntity.ok().body(medicoService.buscarMedicoPorEmail(email));
    }

    @GetMapping("/crm")
    public ResponseEntity<MedicoDTO> getMedicoByCrm(@RequestParam String crm) {
        return ResponseEntity.ok().body(medicoService.buscarMedicoPorCrm(crm));
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<MedicoDTO> removeMedico(@PathVariable Integer id) {
        medicoService.excluirMedico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

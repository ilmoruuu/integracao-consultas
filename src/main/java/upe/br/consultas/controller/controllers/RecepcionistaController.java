package upe.br.consultas.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upe.br.consultas.business.services.interfaces.RecepcionistaService;
import upe.br.consultas.controller.DTO.recepcionista.LoginDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaCriadaDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/recepcionista")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaService service;

    @PostMapping
    public ResponseEntity<RecepcionistaDTO> cadastrar(@RequestBody RecepcionistaCriadaDTO dto) {
        return ResponseEntity.ok(service.cadastrarRecepcionista(dto));
    }

    @PutMapping
    public ResponseEntity<RecepcionistaDTO> atualizar(@RequestBody RecepcionistaDTO dto) {
        return ResponseEntity.ok(service.atualizarRecepcionista(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluirRecepcionista(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<RecepcionistaDTO>> listar() {
        return ResponseEntity.ok(service.listarRecepcionistas());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RecepcionistaDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<RecepcionistaDTO>> buscarPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping("/login")
    public ResponseEntity<RecepcionistaDTO> login(@RequestBody LoginDTO login) {
        try {
            RecepcionistaDTO dto = service.logar(login);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            // Se as credenciais estiverem erradas, retorna 401 Unauthorized
            return ResponseEntity.status(401).build();
        }
    }

}
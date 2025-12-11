package upe.br.consultas.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upe.br.consultas.business.services.interfaces.ConsultaService;
import upe.br.consultas.controller.DTO.consulta.ConsultaCriadaDTO;
import upe.br.consultas.controller.DTO.consulta.ConsultaDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;
import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/consultas")
public class WebConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public String criarConsulta(@RequestParam LocalDate data,
            @RequestParam String descricao,
            @RequestParam Integer medicoId,
            @RequestParam Integer pacienteId,
            @RequestParam(required = false) String materiaisRequisitados,
            @RequestParam Double valor,
            @RequestParam CategoriaProdutoEnum categoria,
            @RequestParam Integer quantidadeMaterial,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        RecepcionistaDTO recepcionista = (RecepcionistaDTO) session.getAttribute("recepcionista");
        if (recepcionista == null) {
            return "redirect:/login";
        }

        try {
            List<String> materiais = materiaisRequisitados != null && !materiaisRequisitados.trim().isEmpty()
                    ? Arrays.stream(materiaisRequisitados.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList())
                    : List.of();

            ConsultaCriadaDTO dto = new ConsultaCriadaDTO(
                    data, descricao, medicoId, pacienteId,
                    recepcionista.id(), materiais, valor,
                    categoria, quantidadeMaterial);

            consultaService.agendarConsulta(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Consulta criada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao criar consulta: " + e.getMessage());
        }

        return "redirect:/consultas";
    }

    @PostMapping("/atualizar")
    public String atualizarConsulta(@RequestParam Integer id,
            @RequestParam LocalDate data,
            @RequestParam String descricao,
            @RequestParam Integer medicoId,
            @RequestParam Integer pacienteId,
            @RequestParam(required = false) String materiaisRequisitados,
            @RequestParam Double valor,
            @RequestParam CategoriaProdutoEnum categoria,
            @RequestParam Integer quantidadeMaterial,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        RecepcionistaDTO recepcionista = (RecepcionistaDTO) session.getAttribute("recepcionista");
        if (recepcionista == null) {
            return "redirect:/login";
        }

        try {
            // Busca a consulta existente para obter os DTOs
            ConsultaDTO consultaExistente = consultaService.buscarConsultaPorId(id);

            List<String> materiais = materiaisRequisitados != null && !materiaisRequisitados.trim().isEmpty()
                    ? Arrays.stream(materiaisRequisitados.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList())
                    : List.of();

            ConsultaDTO dto = new ConsultaDTO(
                    id, data, descricao,
                    consultaExistente.medico(),
                    consultaExistente.paciente(),
                    consultaExistente.recepcionista(),
                    materiais, valor, categoria, quantidadeMaterial);

            consultaService.atualizarConsulta(dto);
            redirectAttributes.addFlashAttribute("sucesso", "Consulta atualizada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar consulta: " + e.getMessage());
        }

        return "redirect:/consultas";
    }

    @PostMapping("/deletar/{id}")
    public String deletarConsulta(@PathVariable Integer id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        RecepcionistaDTO recepcionista = (RecepcionistaDTO) session.getAttribute("recepcionista");
        if (recepcionista == null) {
            return "redirect:/login";
        }

        try {
            consultaService.desmarcarConsulta(id);
            redirectAttributes.addFlashAttribute("sucesso", "Consulta cancelada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao cancelar consulta: " + e.getMessage());
        }

        return "redirect:/consultas";
    }
}

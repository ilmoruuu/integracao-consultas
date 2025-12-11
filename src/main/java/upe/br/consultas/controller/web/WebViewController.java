package upe.br.consultas.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import upe.br.consultas.business.services.interfaces.ConsultaService;
import upe.br.consultas.business.services.interfaces.MedicoService;
import upe.br.consultas.business.services.interfaces.PacienteService;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;
import upe.br.consultas.infra.enums.CategoriaProdutoEnum;

@Controller
public class WebViewController {

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    // Intercepta todas as páginas protegidas e verifica se há sessão
    private boolean checkSession(HttpSession session, Model model) {
        RecepcionistaDTO recepcionista = (RecepcionistaDTO) session.getAttribute("recepcionista");
        if (recepcionista == null) {
            return false;
        }
        model.addAttribute("recepcionista", recepcionista);
        return true;
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("recepcionista") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        return "dashboard";
    }

    @GetMapping("/consultas")
    public String consultasList(HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("consultas", consultaService.listarConsultas());
        return "consultas-list";
    }

    @GetMapping("/consultas/nova")
    public String novaConsulta(HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("medicos", medicoService.listarMedicos());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("categorias", CategoriaProdutoEnum.values());
        return "consulta-form";
    }

    @GetMapping("/consultas/editar/{id}")
    public String editarConsulta(@PathVariable Integer id, HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("consulta", consultaService.buscarConsultaPorId(id));
        model.addAttribute("medicos", medicoService.listarMedicos());
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        model.addAttribute("categorias", CategoriaProdutoEnum.values());
        return "consulta-form";
    }

    @GetMapping("/medicos")
    public String medicosList(HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("medicos", medicoService.listarMedicos());
        return "medicos-list";
    }

    @GetMapping("/pacientes")
    public String pacientesList(HttpSession session, Model model) {
        if (!checkSession(session, model)) {
            return "redirect:/login";
        }
        model.addAttribute("pacientes", pacienteService.listarPacientes());
        return "pacientes-list";
    }
}

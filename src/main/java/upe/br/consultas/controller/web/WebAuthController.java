package upe.br.consultas.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import upe.br.consultas.business.services.interfaces.ConsultaService;
import upe.br.consultas.business.services.interfaces.MedicoService;
import upe.br.consultas.business.services.interfaces.PacienteService;
import upe.br.consultas.business.services.interfaces.RecepcionistaService;
import upe.br.consultas.controller.DTO.recepcionista.LoginDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;

@Controller
public class WebAuthController {

    @Autowired
    private RecepcionistaService recepcionistaService;

    @GetMapping("/login")
    public String loginPage(HttpSession session) {
        // Se já estiver logado, redireciona para o dashboard
        if (session.getAttribute("recepcionista") != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            Model model) {
        try {
            LoginDTO loginDTO = new LoginDTO(email, senha);
            RecepcionistaDTO recepcionista = recepcionistaService.logar(loginDTO);

            // Armazena recepcionista na sessão
            session.setAttribute("recepcionista", recepcionista);

            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Email ou senha inválidos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

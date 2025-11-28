package upe.br.consultas.controller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upe.br.consultas.business.services.interfaces.MedicoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

}

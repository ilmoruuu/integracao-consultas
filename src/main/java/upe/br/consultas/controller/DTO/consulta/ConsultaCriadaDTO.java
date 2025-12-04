package upe.br.consultas.controller.DTO.consulta;

import java.time.LocalDate;
import java.util.List;

public record ConsultaCriadaDTO(
        LocalDate data,
        String descricao,
        Integer medicoId,
        Integer pacienteId,
        Integer recepcionistaId,
        List<String> materiaisRequisitados,
        double valorConsulta
) {}
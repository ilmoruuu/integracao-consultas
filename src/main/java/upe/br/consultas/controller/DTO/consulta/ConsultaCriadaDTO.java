package upe.br.consultas.controller.DTO.consulta;

import java.time.LocalDate;

public record ConsultaCriadaDTO(
        LocalDate data,
        String descricao,
        Integer medicoId,
        Integer pacienteId,
        Integer recepcionistaId 
) {}
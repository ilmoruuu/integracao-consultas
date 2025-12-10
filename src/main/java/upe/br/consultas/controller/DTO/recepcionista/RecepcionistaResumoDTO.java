package upe.br.consultas.controller.DTO.recepcionista;

import upe.br.consultas.infra.entities.Recepcionista; 

public record RecepcionistaResumoDTO(Integer id, String nome) {
    public static RecepcionistaResumoDTO fromEntity(Recepcionista recepcionista) {
        return new RecepcionistaResumoDTO(recepcionista.getId(), recepcionista.getNome());
    }
}
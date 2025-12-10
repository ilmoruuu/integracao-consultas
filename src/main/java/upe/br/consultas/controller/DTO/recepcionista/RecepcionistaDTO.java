package upe.br.consultas.controller.DTO.recepcionista;

import upe.br.consultas.infra.entities.Recepcionista;

public record RecepcionistaDTO(
        Integer id,
        String nome,
        String telefone,
        String email
) {
    public static RecepcionistaDTO recepcionistaToDTO(Recepcionista entity) {
        return new RecepcionistaDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail()
        );
    }
}

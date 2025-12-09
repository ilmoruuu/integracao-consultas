package upe.br.consultas.controller.DTO.recepcionista;

import upe.br.consultas.infra.entities.Recepcionista;

public record RecepcionistaDTO(

    Integer id,
    String nome,
    String telefone,
    String email

) {

    public RecepcionistaDTO recepcionistaDTO (Recepcionista recepcionista) {
        return new RecepcionistaDTO(
                recepcionista.getId(),
                recepcionista.getNome(),
                recepcionista.getTelefone(),
                recepcionista.getEmail()
        );
    }

}

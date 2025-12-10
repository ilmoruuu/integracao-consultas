package upe.br.consultas.controller.DTO.recepcionista;
public record RecepcionistaCriadaDTO(
        String nome,
        String telefone,
        String email,
        String senha // Recebe a senha no momento da criação
) {}
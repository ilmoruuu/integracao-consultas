package upe.br.consultas.infra.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException {
    public MedicoNaoEncontradoException() {
        super("O médico não pertence ao nosso banco de dados!");
    }
}

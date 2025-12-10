package upe.br.consultas.infra.exceptions;

public class PacienteNaoEncontradoException extends RuntimeException {
    public PacienteNaoEncontradoException() {
        super("O paciente não pertence ao nosso banco de dados!");
    }
}

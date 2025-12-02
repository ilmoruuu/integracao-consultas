package upe.br.consultas.infra.exceptions;

public class PacienteExistenteException extends RuntimeException {
    public PacienteExistenteException() {
        super("Esse Paciente já existe! Cadastre outro CPF!");
    }
}

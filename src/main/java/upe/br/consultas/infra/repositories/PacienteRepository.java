package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Paciente;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Override
    Optional<Paciente> findById(Integer id);
    List<Paciente> findByNomeContainingIgnoreCase(String nome);
    Optional<Paciente> findByCpf(String cpf);
    Optional<Paciente> findByEmail(String email);
}

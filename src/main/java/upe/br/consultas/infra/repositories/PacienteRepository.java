package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {

}

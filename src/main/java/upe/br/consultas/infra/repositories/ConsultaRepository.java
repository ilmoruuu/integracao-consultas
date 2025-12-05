package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Consulta;
import upe.br.consultas.infra.entities.Medico;
import upe.br.consultas.infra.entities.Paciente;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    
    List<Consulta> findByPacienteId(Integer pacienteId);
    List<Consulta> findByMedicoId(Integer medicoId);
    // Busca consultas navegando: Consulta -> Medico -> Nome
    // SQL gerado: SELECT * FROM consulta c JOIN medico m ON c.medico_id = m.id WHERE UPPER(m.nome) LIKE UPPER('%nome%')
    List<Consulta> findByMedicoNomeContainingIgnoreCase(String nome);
    List<Consulta> findByData(LocalDate data);
    List<Consulta> findByMedicoIdAndData(Integer medicoId, LocalDate data);
    List<Consulta> findByPacienteNomeContainingIgnoreCase(String nome);
    List<Consulta> findByPacienteIdAndData(Integer pacienteId, LocalDate data);
}
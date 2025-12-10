package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Medico;
import upe.br.consultas.infra.enums.EspecialidadesEnum;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findMedicoByCrm (String crm);
    Optional<Medico> findMedicoById(Integer id);
    Optional<Medico> findMedicoByEspecializacao(EspecialidadesEnum especializacao);
    Optional<Medico> findMedicoByEmail(String email);
    Optional<Medico> findMedicoByNome(String nome);


}
package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Recepcionista;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Integer> {

    Optional<Recepcionista> findRecepcionistaById(Integer id);
    List<Recepcionista> findByNomeContainingIgnoreCase(String nome);
    Optional<Recepcionista> findByEmail(String email);

}
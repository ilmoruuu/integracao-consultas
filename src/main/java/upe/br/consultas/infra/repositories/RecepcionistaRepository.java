package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Recepcionista;

import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepcionista, Integer> {

    Optional<Recepcionista> findRecepcionistaById(Integer id);
    Optional<Recepcionista> findRecepcionistaByNome(String nome);

}
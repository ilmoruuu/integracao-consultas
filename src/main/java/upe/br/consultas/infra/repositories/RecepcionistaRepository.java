package upe.br.consultas.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import upe.br.consultas.infra.entities.Recepicionista;

import java.util.Optional;

@Repository
public interface RecepcionistaRepository extends JpaRepository<Recepicionista, Integer> {

    Optional<Recepicionista> findRecepicionistaById(Integer id);
    Optional<Recepicionista> findRecepicionistaByNome(String nome);

}
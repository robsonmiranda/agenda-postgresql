package br.com.postgresqlAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postgresqlAgenda.model.Contato;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    boolean existsByName(String name);

}

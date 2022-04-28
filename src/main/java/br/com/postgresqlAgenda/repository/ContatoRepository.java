package br.com.postgresqlAgenda.repository;

import br.com.postgresqlAgenda.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, UUID> {

    boolean existsByName(String name);
    List<Contato> findByPhone(String phone);

}

package br.com.postgresqlAgenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.postgresqlAgenda.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}

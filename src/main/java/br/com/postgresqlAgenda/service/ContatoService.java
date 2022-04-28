package br.com.postgresqlAgenda.service;

import br.com.postgresqlAgenda.model.Contato;
import br.com.postgresqlAgenda.repository.ContatoRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContatoService {

    final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    @Transactional
    public Contato save(Contato contato) {
        return contatoRepository.save(contato);
    }

    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    public Optional<Contato> findById(UUID id) {
        return contatoRepository.findById(id);
    }

    public Optional<Contato> findByName(String name) {
        Contato contato = new Contato();
        contato.setName(name);
        Example<Contato> contatoExample = Example.of(contato);
        return contatoRepository.findOne(contatoExample);
    }

    public List<Contato> findByPhone(String phone) {
        return contatoRepository.findByPhone(phone);
    }

    @Transactional
    public void delete(Contato contato) {
        contatoRepository.delete(contato);
    }

    public boolean existByName(String name) {
        return contatoRepository.existsByName(name);
    }
}

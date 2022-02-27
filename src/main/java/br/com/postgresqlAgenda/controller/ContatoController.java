package br.com.postgresqlAgenda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postgresqlAgenda.model.Contato;
import br.com.postgresqlAgenda.repository.ContatoRepository;

@RestController
@RequestMapping("/contato")
public class ContatoController {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/{id}")
    public Contato getContato(@PathVariable("id") Long id) {
        if (id != null) {
           Optional<Contato> contato = this.contatoRepository.findById(id);
           if (contato.isPresent()) {
               return contato.get();
           }
        }
        return null;
    }

    @PostMapping("/")
    public Contato setContato(@RequestBody Contato contato) {
        return this.contatoRepository.save(contato);
    }
}

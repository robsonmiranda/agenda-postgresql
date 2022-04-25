package br.com.postgresqlAgenda.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postgresqlAgenda.exception.ResourceNotFoundException;
import br.com.postgresqlAgenda.model.Contato;
import br.com.postgresqlAgenda.repository.ContatoRepository;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/contatos")
public class ContatoController {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping("/{id}")
    public Contato getContatoById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        if (id != null) {
           Optional<Contato> contato = this.contatoRepository.findById(id);
           if (contato.isPresent()) {
               return contato.get();
           }
        }
        return null;
    }

    @PostMapping("/")
    public Contato createContato(@RequestBody Contato contato) {
        return this.contatoRepository.save(contato);
    }

    @PutMapping("/{id}")
    public Contato updateContato(@PathVariable("id") Long id, @RequestBody Contato contatoDetails) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado para este id :: " + id));
        contato.setName(contatoDetails.getName());
        contato.setPhone(contatoDetails.getPhone());
        return contatoRepository.save(contato);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteContato(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado para este id :: " + id));
        contatoRepository.delete(contato);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

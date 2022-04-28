package br.com.postgresqlAgenda.controller;

import br.com.postgresqlAgenda.dto.ContatoDto;
import br.com.postgresqlAgenda.model.Contato;
import br.com.postgresqlAgenda.service.ContatoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/contato")
public class ContatoController {

    final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveContato(@RequestBody @Valid ContatoDto contatoDto) {
        if (contatoService.existByName(contatoDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: This name is already in use!");
        }
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoDto, contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.save(contato));
    }

    @GetMapping
    public ResponseEntity<List<Contato>> getAllContatos() {
        return ResponseEntity.status(HttpStatus.OK).body(contatoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getContatoById(@PathVariable(value = "id") UUID id) {
        Optional<Contato> optionalContato = contatoService.findById(id);
        return optionalContato.<ResponseEntity<Object>>
                map(contato -> ResponseEntity.status(HttpStatus.OK).body(contato))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato not found!"));
    }

    @GetMapping("/name={name}")
    public ResponseEntity<Object> getContatoByName(@PathVariable(value = "name") String name) {
        Optional<Contato> optionalContato = contatoService.findByName(name);
        return optionalContato.<ResponseEntity<Object>>
                map(contato -> ResponseEntity.status(HttpStatus.OK).body(contato))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato not found!"));
    }

    @GetMapping("/phone={phone}")
    public ResponseEntity<Object> getContatoByPhone(@PathVariable(value = "phone") String phone) {
        List<Contato> contatoList = contatoService.findByPhone(phone);
        if (contatoList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contatoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContato(@PathVariable(value = "id") UUID id) {
        Optional<Contato> optionalContato = contatoService.findById(id);
        if (optionalContato.isPresent()) {
            contatoService.delete(optionalContato.get());
            return ResponseEntity.status(HttpStatus.OK).body("Contato deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato not found!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateContato(@PathVariable(value = "id") UUID id, @RequestBody @Valid ContatoDto contatoDto) {
        Optional<Contato> optionalContato = contatoService.findById(id);
        if (optionalContato.isPresent()) {
            Contato contato = new Contato();
            BeanUtils.copyProperties(contatoDto, contato);
            contato.setId(optionalContato.get().getId());
            contato.setName(optionalContato.get().getName());
            return ResponseEntity.status(HttpStatus.OK).body(contatoService.save(contato));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato not found!");
    }
}

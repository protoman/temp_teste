package net.upperland.project01.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.dto.ClientDTO;
import net.upperland.project01.model.entity.ClientEntity;
import net.upperland.project01.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/practice")
@RequiredArgsConstructor
public class PracticeController {

    private final ClientService clientService; // for auto-constructors, needs final

    private static final Logger logger = LoggerFactory.getLogger(PracticeController.class);

    @GetMapping("client/{name}")
    public ResponseEntity<?> findClient(@PathVariable String name) {
        return ResponseEntity.ok().body(clientService.findClientByName(name));
    }

    @PostMapping("client")
    // TODO: handle validation error and show message
    public ResponseEntity<ClientEntity> addClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok().body(clientService.saveClient(clientDTO));
    }

    @PutMapping("client/{id}")
    // TODO: handle validation error and show message
    public ResponseEntity<ClientEntity> updateClient(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO) throws Exception {
        return ResponseEntity.ok().body(clientService.updateClientById(id, clientDTO));
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<ClientEntity> deleteClientById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(clientService.deleteClientById(id));
    }

    @GetMapping("remote-client/{id}")
    public ResponseEntity<?> findRemoteClient(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(clientService.findRemoteClientByName(id));
    }

}

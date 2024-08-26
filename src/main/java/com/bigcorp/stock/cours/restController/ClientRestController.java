package com.bigcorp.stock.cours.restController;

import com.bigcorp.stock.cours.model.Client;
import com.bigcorp.stock.cours.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable("id") Long id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Client client = this.clientService.findById(id);
        if(client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity getAllClients() {
        List<Client> allClients = this.clientService.findAll();
        if(allClients == null || allClients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allClients);
    }

    @PostMapping
    public Client save(@RequestBody Client client) {
       return this.clientService.save(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClientById(@PathVariable("id") Long id) {
        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Client client = this.clientService.findById(id);
        if(client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        this.clientService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

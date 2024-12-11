package com.arman.springhotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    // Получить всех клиентов
    @GetMapping
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Найти клиента по имени
    @GetMapping("/name/{name}")
    public List<Client> getClientsByName(@PathVariable String name) {
        return clientRepository.findByName(name);
    }

    // Найти клиента по email
    @GetMapping("/email/{email}")
    public Optional<Client> getClientByEmail(@PathVariable String email) {
        return clientRepository.findByEmail(email);
    }

    // Создать клиента
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id));
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id));
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        return clientRepository.save(client);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable int id) {
        if (!clientRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    @GetMapping("/search")
    public List<Client> searchClientsByName(@RequestParam String name) {
        return clientRepository.findByNameContainingIgnoreCase(name);
    }
}

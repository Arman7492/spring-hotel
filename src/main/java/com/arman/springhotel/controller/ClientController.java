package com.arman.springhotel.controller;

import com.arman.springhotel.entity.Client;
import com.arman.springhotel.service.ClientService;
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
    private ClientService clientService;

    // Получить всех клиентов
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Найти клиента по имени
    @GetMapping("/name/{name}")
    public List<Client> getClientsByName(@PathVariable String name) {
        return clientService.getClientsByName(name);
    }

    // Найти клиента по email
    @GetMapping("/email/{email}")
    public Optional<Client> getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    // Создать клиента
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client createClient(@RequestBody Client client) {
        return clientService.createOrUpdateClient(client);
    }

    // Получить клиента по ID
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable int id) {
        return clientService.getClientById(id);
    }

    // Обновить клиента
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        Client client = clientService.getClientById(id);
        client.setName(updatedClient.getName());
        client.setEmail(updatedClient.getEmail());
        return clientService.createOrUpdateClient(client);
    }

    // Удалить клиента
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
    }

    // Поиск клиента по подстроке имени
    @GetMapping("/search")
    public List<Client> searchClientsByName(@RequestParam String name) {
        return clientService.searchClientsByName(name);
    }
}

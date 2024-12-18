package com.arman.springhotel.service;

import com.arman.springhotel.entity.Client;
import com.arman.springhotel.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    public Client createOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    public List<Client> getClientsByName(String name) {
        return clientRepository.findByName(name);
    }

    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> searchClientsByName(String namePart) {
        return clientRepository.findByNameContainingIgnoreCase(namePart);
    }
}

package com.arman.springhotel.service;

import com.arman.springhotel.entity.Manager;
import com.arman.springhotel.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    public Manager createOrUpdateManager(Manager manager) {

        Manager updatedManager = new Manager();
        updatedManager.setId(manager.getId());
        updatedManager.setName(manager.getName());
        updatedManager.setEmail(manager.getEmail());
        updatedManager.setPhoneNumber(manager.getPhoneNumber());
        return managerRepository.save(updatedManager);
    }

    public Optional<Manager> getManagerById(Long id) {
        return managerRepository.findById(id);
    }

    public Optional<Manager> getManagerByEmail(String email) {
        return managerRepository.findByEmail(email);
    }

    public Optional<Manager> getManagerByName(String name) {
        return managerRepository.findByName(name);
    }

    public void deleteManager(Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Manager not found with id: " + id);
        }
    }
}

package com.arman.springhotel.controller;

import com.arman.springhotel.entity.Manager;
import com.arman.springhotel.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = managerService.getAllManagers();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        return managerService.getManagerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Manager> getManagerByEmail(@PathVariable String email) {
        return managerService.getManagerByEmail(email)
                .map(manager -> {
                    Manager response = new Manager();
                    response.setId(manager.getId());
                    response.setName(manager.getName());
                    response.setEmail(manager.getEmail());
                    response.setPhoneNumber(manager.getPhoneNumber());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Manager> getManagerByName(@PathVariable String name) {
        return managerService.getManagerByName(name)
                .map(manager -> {
                    Manager response = new Manager();
                    response.setId(manager.getId());
                    response.setName(manager.getName());
                    response.setEmail(manager.getEmail());
                    response.setPhoneNumber(manager.getPhoneNumber());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Manager> createOrUpdateManager(@RequestBody Manager manager) {
        Manager savedManager = managerService.createOrUpdateManager(manager);
        return ResponseEntity.ok(savedManager);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}

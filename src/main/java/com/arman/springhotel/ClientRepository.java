package com.arman.springhotel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    // Найти клиента по имени
    List<Client> findByName(String name);

    // Найти клиента по email
    Optional<Client> findByEmail(String email);

    // Найти всех клиентов, чье имя содержит подстроку (игнорируя регистр)
    List<Client> findByNameContainingIgnoreCase(String namePart);
}

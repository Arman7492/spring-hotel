package com.arman.springhotel.repository;

import com.arman.springhotel.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByName(String name);

    Optional<Client> findByEmail(String email);

    List<Client> findByNameContainingIgnoreCase(String namePart);
}

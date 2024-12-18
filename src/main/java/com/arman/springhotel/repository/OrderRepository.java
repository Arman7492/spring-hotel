package com.arman.springhotel.repository;

import com.arman.springhotel.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // Метод для поиска заказов по статусу
    List<Order> findByStatus(String status);
}

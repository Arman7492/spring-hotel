package com.arman.springhotel.controller;

import com.arman.springhotel.entity.Order;
import com.arman.springhotel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        Order order = orderService.getOrderById(id);
        if (order != null) {
            order.setStatus(status);
            orderService.createOrUpdateOrder(order);
            return ResponseEntity.ok("Status updated");
        }
        return ResponseEntity.badRequest().body("Order not found");
    }

    @GetMapping("/pending")
    public List<Order> getPendingOrders() {
        return orderService.findByStatus("PENDING");
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.createOrUpdateOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order orderDetails) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        order.setClientId(orderDetails.getClientId());
        order.setRoomId(orderDetails.getRoomId());
        order.setStartDate(orderDetails.getStartDate());
        order.setEndDate(orderDetails.getEndDate());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());

        Order updatedOrder = orderService.createOrUpdateOrder(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

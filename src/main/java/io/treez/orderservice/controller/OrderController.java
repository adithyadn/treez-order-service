package io.treez.orderservice.controller;

import io.treez.orderservice.model.Order;
import io.treez.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> listOrders() {
        return ResponseEntity.ok(orderService.listAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "id") long orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(name = "id") long orderId
            , @RequestBody Order order) {

        return ResponseEntity.ok(orderService.updateOrder(orderId, order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable(name = "id") long orderId) {

        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(orderId));
    }

}

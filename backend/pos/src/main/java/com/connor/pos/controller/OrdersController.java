package com.connor.pos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connor.pos.model.Orders;
import com.connor.pos.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins="*")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersRepository ordersRepository;

    @GetMapping
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Long id) {
        return ordersRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders saved = ordersRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}

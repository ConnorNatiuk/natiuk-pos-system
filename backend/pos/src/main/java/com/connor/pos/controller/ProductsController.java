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

import com.connor.pos.model.Products;
import com.connor.pos.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5500")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsRepository productsRepository;

    @GetMapping
    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> findById(@PathVariable Long id) {
        return productsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<Products> findBySku(@PathVariable String sku) {
        return productsRepository.findBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        Products saved = productsRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}

package com.connor.pos.controller;

import com.connor.pos.model.Products;
import com.connor.pos.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5500")
@RequiredArgsConstructor
public class ProductController {

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

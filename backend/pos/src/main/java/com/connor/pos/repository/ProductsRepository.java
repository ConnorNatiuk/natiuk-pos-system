package com.connor.pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connor.pos.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    Optional<Products> findBySku(String sku);
}

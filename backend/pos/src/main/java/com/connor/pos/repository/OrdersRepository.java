package com.connor.pos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connor.pos.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    @Override
    Optional<Orders> findById(Long id);
}

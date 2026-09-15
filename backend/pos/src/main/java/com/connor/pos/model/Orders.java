package com.connor.pos.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long customerId;

    @Column
    private BigDecimal totalAmount;
    
    @Column
    private Date orderDate;
}   

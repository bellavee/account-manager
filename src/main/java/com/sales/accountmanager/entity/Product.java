package com.sales.accountmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    private String colorHex;

    private BigDecimal price;

    private BigDecimal principalAmount;

    private BigDecimal currencyRate;

    private BigDecimal converter;

    private BigDecimal intAmount;

    private int interest;

    private Date createdTime;

    private Date updatedTime;

}

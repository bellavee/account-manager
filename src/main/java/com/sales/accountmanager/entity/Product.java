package com.sales.accountmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    private Long id;

    private String name;

    private String description;

    private Date createdTime;

    private Date updatedTime;

}

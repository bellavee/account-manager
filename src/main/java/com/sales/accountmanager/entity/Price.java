package com.sales.accountmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private BigDecimal originalPrice;

    private BigDecimal discountedPrice;

    private BigDecimal principalAmount;

    private BigDecimal interestRate;

    private LocalDateTime validFrom;

    private LocalDateTime validTo;

    private Date createdTime;

    private Date updatedTime;

}

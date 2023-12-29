package com.sales.accountmanager.entity;

import com.sales.accountmanager.utils.StringListConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String description;

    private BigDecimal crystal;

    private BigDecimal bigNote;

    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    private Boolean isKept;

    private Boolean isSold;

    private Date createdTime;

    private Date updatedTime;

}

package com.sales.accountmanager.entity;

import com.sales.accountmanager.utils.StringListConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", allocationSize = 1)
    private Long id;

    private Long productId;

    private String name;

    private String description;

    private int crystal;

    private int bigNote;

    @Convert(converter = StringListConverter.class)
    private List<String> tags;

    private Boolean isKept;

    private Boolean isSold;

    private String imageUrl;

    private Date createdTime;

    private Date updatedTime;


}

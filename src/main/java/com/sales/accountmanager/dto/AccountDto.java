package com.sales.accountmanager.dto;

import com.sales.accountmanager.entity.Account;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private Long productId;

    private ProductDto product;

    private String name;

    private String description;

    private int crystal;

    private int bigNote;

    private List<String> tags;

    private Boolean isKept = false;

    private Boolean isSold = false;

    private String imageBase64;

    private String imageUrl;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public AccountDto(Account item,ProductDto product) {
        CommonUtils.copyNonNullProperties(item, this);
        this.product = product;
    }

}

package com.sales.accountmanager.dto;

import com.sales.accountmanager.entity.Account;
import com.sales.accountmanager.entity.Image;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.StringListConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    private MultipartFile imageUpload;

    private String imagePublicId;

    private Image image;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    public AccountDto(Account item, ProductDto product, Image image) {
        CommonUtils.copyNonNullProperties(item, this);
        if (!ObjectUtils.isEmpty(product)) this.product = product;
        if (!ObjectUtils.isEmpty(image)) this.image = image;

    }

}

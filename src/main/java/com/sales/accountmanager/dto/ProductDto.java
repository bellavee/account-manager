package com.sales.accountmanager.dto;

import com.sales.accountmanager.entity.Product;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.NumberUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private String colorHex;

    private String price;

    private String principalAmount;

    private String currencyRate;

    private String converter;

    private String intAmount;

    private int interest;

    public ProductDto(Product item) {
        CommonUtils.copyNonNullProperties(item, this);
        this.price = NumberUtils.formatBigDecimal(item.getPrice());
        this.principalAmount = NumberUtils.formatBigDecimal(item.getPrincipalAmount());
        this.currencyRate = NumberUtils.formatBigDecimal(item.getCurrencyRate());
        this.converter = NumberUtils.formatBigDecimal(item.getConverter());
        this.intAmount = NumberUtils.formatBigDecimal(item.getIntAmount());
    }

}

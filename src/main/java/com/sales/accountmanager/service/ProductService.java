package com.sales.accountmanager.service;

import com.sales.accountmanager.dto.ProductDto;
import com.sales.accountmanager.entity.Product;
import com.sales.accountmanager.model.ApiResult;
import com.sales.accountmanager.repository.ProductRepository;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.DateUtils;
import com.sales.accountmanager.utils.NumberUtils;
import com.sales.accountmanager.utils.ServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public List<ProductDto> findAll() {
        List<Product> list = repository.findAllByOrderByCreatedTimeDesc();
        return ServiceUtils.convertToDto(list, this::mapping);
    }

    public ProductDto findById(Long id) {
        return ServiceUtils.findById(id, repository::findById, this::mapping);
    }

    private ProductDto mapping(Product item) {
        return new ProductDto(item);
    }

    public ApiResult<ProductDto> save(ProductDto dto) {
        return ServiceUtils.saveGeneric(
                dto,
                ProductDto::getId,
                repository::findById,
                Product::new,
                (dtoToSave, entity) -> {
                    CommonUtils.copyNonNullProperties(dtoToSave, entity);
                    BigDecimal price = NumberUtils.parseFormattedString(dto.getPrice());
                    BigDecimal principalAmount = NumberUtils.parseFormattedString(dto.getPrincipalAmount());
                    BigDecimal currencyRate = NumberUtils.parseFormattedString(dto.getCurrencyRate());
                    BigDecimal converter = principalAmount.multiply(currencyRate);
                    BigDecimal intAmount = price.subtract(converter);
                    BigDecimal interestDecimal = intAmount.divide(price, 2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
                    int interest = interestDecimal.intValue();

                    entity.setPrice(price);
                    entity.setPrincipalAmount(principalAmount);
                    entity.setCurrencyRate(currencyRate);
                    entity.setConverter(converter);
                    entity.setIntAmount(intAmount);
                    entity.setInterest(interest);
                    return repository.save(entity);
                },
                this::findAll,
                (entity, timestamp) -> entity.setCreatedTime(DateUtils.asDate(timestamp)),
                (entity, timestamp) -> entity.setUpdatedTime(DateUtils.asDate(timestamp)),
                log
        );
    }

}

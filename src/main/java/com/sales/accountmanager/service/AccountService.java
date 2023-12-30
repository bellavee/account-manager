package com.sales.accountmanager.service;

import com.sales.accountmanager.dto.AccountDto;
import com.sales.accountmanager.dto.ProductDto;
import com.sales.accountmanager.entity.Account;
import com.sales.accountmanager.model.ApiResult;
import com.sales.accountmanager.repository.AccountRepository;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.DateUtils;
import com.sales.accountmanager.utils.ServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository repository;
    private final ProductService productService;

    public List<AccountDto> findAll() {
        List<Account> list = repository.findAllByOrderByCreatedTimeDesc();
        return ServiceUtils.convertToDto(list, this::mapping);
    }

    public AccountDto findById(Long id) {
        return ServiceUtils.findById(id, repository::findById, this::mapping);
    }

    private AccountDto mapping(Account item) {
        ProductDto product = productService.findById(item.getProductId());
        return new AccountDto(item, product);
    }

    public ApiResult<AccountDto> save(AccountDto dto) {
        return ServiceUtils.saveGeneric(
                dto,
                AccountDto::getId,
                repository::findById,
                Account::new,
                (dtoToSave, entity) -> {
                    CommonUtils.copyNonNullProperties(dtoToSave, entity);
                    return repository.save(entity);
                },
                this::findAll,
                (entity, timestamp) -> entity.setCreatedTime(DateUtils.asDate(timestamp)),
                (entity, timestamp) -> entity.setUpdatedTime(DateUtils.asDate(timestamp)),
                log
        );
    }

}

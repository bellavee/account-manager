package com.sales.accountmanager.service;

import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.dto.AccountDto;
import com.sales.accountmanager.dto.ProductDto;
import com.sales.accountmanager.entity.Account;
import com.sales.accountmanager.entity.Image;
import com.sales.accountmanager.model.ApiResult;
import com.sales.accountmanager.repository.AccountRepository;
import com.sales.accountmanager.repository.ImageRepository;
import com.sales.accountmanager.utils.CommonUtils;
import com.sales.accountmanager.utils.DateUtils;
import com.sales.accountmanager.utils.ServiceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository repository;
    private final ProductService productService;
    private final ImageRepository imageRepository;

    public List<AccountDto> findAll() {
        List<Account> list = repository.findAllByOrderByCreatedTimeAsc();
        return ServiceUtils.convertToDto(list, this::mapping);
    }

    public List<AccountDto> findByProductId(Long productId) {
        List<Account> list = repository.findByProductId(productId);
        return ServiceUtils.convertToDto(list, this::mapping);
    }

    public AccountDto findById(Long id) {
        return ServiceUtils.findById(id, repository::findById, this::mapping);
    }

    private AccountDto mapping(Account item) {
        ProductDto product = productService.findById(item.getProductId());
        Image image = imageRepository.findByPublicId(item.getImagePublicId());
        return new AccountDto(item, product, image);
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

    public ApiResult<Account> uploadImage(Long id, String imagePublicId) {
        Optional<Account> item = repository.findById(id);
        ApiResult<Account> result = new ApiResult<>();
        if (item.isPresent()) {
            item.get().setImagePublicId(imagePublicId);
            item.get().setUpdatedTime(DateUtils.asDate(LocalDateTime.now()));
            repository.save(item.get());

            result.setResCode(ApiConstant.ResCode.SUCCESS);
            result.setResDesc(ApiConstant.ResDesc.SUCCESS);
            return result;
        } else {
            result.setResCode(ApiConstant.ResCode.FAIL);
            result.setResDesc(ApiConstant.ResDesc.FAIL);
            return result;
        }
    }
}

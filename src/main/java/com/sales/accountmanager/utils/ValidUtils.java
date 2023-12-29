package com.sales.accountmanager.utils;

import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.model.ApiResult;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class ValidUtils {

    public static <T> ApiResult<String> checkValidForm(@Valid T dto) {
        ApiResult<String> result = new ApiResult<>();
        List<String> errors = new ArrayList<>();

        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> violations = validator.validate(dto);

            if (!violations.isEmpty()) {
                violations.forEach(violation -> errors.add(violation.getMessage()));
                result.setResCode(ApiConstant.ResCode.ERR_VALID);
                result.setResDesc(ApiConstant.ResDesc.ERR_VALID);
                result.setData(errors);
            } else {
                result.setResCode(ApiConstant.ResCode.SUCCESS);
                result.setResDesc(ApiConstant.ResDesc.SUCCESS);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return result;
    }

}

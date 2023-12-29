package com.sales.accountmanager.utils;

import com.sales.accountmanager.constant.ApiConstant;
import com.sales.accountmanager.model.ApiResult;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import org.springframework.ui.Model;

import java.util.function.Function;

public class ControllerHelper {

    public static <T> HtmxResponse saveGeneric(
            T dto,
            Function<T, ApiResult<T>> saveFunction,
            Model model,
            String fragmentPath,
            String successMessage) {

        ApiResult<String> validator = ValidUtils.checkValidForm(dto);
        if (!validator.getResCode().equals(ApiConstant.ResCode.SUCCESS)) {
            return HtmxUtils.showMsg(String.join(". ", validator.getData()), validator.getResCode());
        } else {
            ApiResult<T> result = saveFunction.apply(dto);
            if (ApiConstant.ResCode.SUCCESS.equals(result.getResCode())) {
                model.addAttribute("data", result.getData());
                return HtmxUtils.showSuccessMsg(fragmentPath + " :: table", successMessage, result.getResCode());
            } else {
                return HtmxUtils.showMsg(result.getResDesc(), result.getResCode());
            }
        }

    }

    public static <ID> HtmxResponse deleteGeneric(
            ID id,
            Function<ID, ApiResult<?>> deleteFunction,
            String successMessage) {

        ApiResult<?> result = deleteFunction.apply(id);
        if (ApiConstant.ResCode.SUCCESS.equals(result.getResCode())) {
            return HtmxUtils.showMsg(successMessage, result.getResCode());
        }
        return HtmxUtils.showMsg("Error happened", result.getResCode());
    }

}

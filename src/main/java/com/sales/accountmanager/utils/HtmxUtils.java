package com.sales.accountmanager.utils;

import com.sales.accountmanager.constant.AppConstant;
import com.sales.accountmanager.model.Message;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxResponse;
import io.github.wimdeblauwe.htmx.spring.boot.mvc.HxTriggerLifecycle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HtmxUtils {

    public static HtmxResponse showSuccessMsg(String template, String successMsg, String resCode) {
        Message toastrMsg = new Message(successMsg, resCode);
        return new HtmxResponse()
                .addTemplate(template)
                .addTrigger(AppConstant.SHOW_MESSAGE, CommonUtils.convertObjectToJson(toastrMsg), HxTriggerLifecycle.RECEIVE);
    }

    public static HtmxResponse showMsg(String errMsg, String resCode) {
        Message toastrMsg = new Message(errMsg, resCode);
        return new HtmxResponse()
                .addTrigger(AppConstant.SHOW_MESSAGE, CommonUtils.convertObjectToJson(toastrMsg), HxTriggerLifecycle.RECEIVE);
    }

}

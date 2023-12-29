package com.sales.accountmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {

    private String resCode;
    private String resDesc;
    private List<T> data;

    public ApiResult(String resCode, String resDesc) {
        this.resCode = resCode;
        this.resDesc = resDesc;
    }
}

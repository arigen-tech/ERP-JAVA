package com.erp.service;

import com.erp.request.MasCurrencyRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCurrencyResponse;

import java.util.List;

public interface MasCurrencyService {

    ApiResponse<MasCurrencyResponse> create(MasCurrencyRequest req);
    ApiResponse<MasCurrencyResponse> updateById(Long id,MasCurrencyRequest req);
    ApiResponse<MasCurrencyResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCurrencyResponse> getById(Long id);
    ApiResponse<List<MasCurrencyResponse>> getAll(Integer flag);
}

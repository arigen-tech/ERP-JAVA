package com.erp.service;

import com.erp.request.MasCountryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCountryResponse;

import java.util.List;

public interface MasCountryService {

    ApiResponse<MasCountryResponse> create(MasCountryRequest req);
    ApiResponse<MasCountryResponse> updateById(Long id,MasCountryRequest req);
    ApiResponse<MasCountryResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCountryResponse> getById(Long id);
    ApiResponse<List<MasCountryResponse>> getAll(Integer flag);
}

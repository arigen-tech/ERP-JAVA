package com.erp.service;

import com.erp.request.MasVillageRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasVillageResponse;

import java.util.List;

public interface MasVillageService {

    ApiResponse<MasVillageResponse> create(MasVillageRequest req);
    ApiResponse<MasVillageResponse> updateById(Long id,MasVillageRequest req);
    ApiResponse<MasVillageResponse> changeStatusById(Long id, String status);
    ApiResponse<MasVillageResponse> getById(Long id);
    ApiResponse<List<MasVillageResponse>> getAll(Integer flag);
}
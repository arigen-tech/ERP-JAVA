package com.erp.service;


import com.erp.request.MasCapacityRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCapacityResponse;

import java.util.List;

public interface MasCapacityService {

    ApiResponse<MasCapacityResponse> create(MasCapacityRequest req);
    ApiResponse<MasCapacityResponse> updateById(Long id,MasCapacityRequest req);
    ApiResponse<MasCapacityResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCapacityResponse> getById(Long id);
    ApiResponse<List<MasCapacityResponse>> getAll(Integer flag);
}

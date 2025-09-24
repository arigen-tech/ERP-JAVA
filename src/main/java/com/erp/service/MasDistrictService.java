package com.erp.service;

import com.erp.request.MasDistrictRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasDistrictResponse;

import java.util.List;

public interface MasDistrictService {

    ApiResponse<MasDistrictResponse> create(MasDistrictRequest req);
    ApiResponse<MasDistrictResponse> updateById(Long id,MasDistrictRequest req);
    ApiResponse<MasDistrictResponse> changeStatusById(Long id,String status);
    ApiResponse<MasDistrictResponse> getById(Long id);
    ApiResponse<List<MasDistrictResponse>> getAll(Integer flag);
}

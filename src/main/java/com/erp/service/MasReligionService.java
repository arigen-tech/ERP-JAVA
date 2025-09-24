package com.erp.service;

import com.erp.request.MasReligionRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasReligionResponse;

import java.util.List;

public interface MasReligionService {

    ApiResponse<MasReligionResponse> create(MasReligionRequest request);
    ApiResponse<MasReligionResponse> updateById(Long id,MasReligionRequest request);
    ApiResponse<MasReligionResponse> changeStatusById(Long id,String status);
    ApiResponse<MasReligionResponse> getById(Long id);
    ApiResponse<List<MasReligionResponse>> getAll(Integer flag);
}

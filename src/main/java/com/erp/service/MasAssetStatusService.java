package com.erp.service;

import com.erp.request.MasAssetStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasAssetStatusResponse;

import java.util.List;

public interface MasAssetStatusService {

    ApiResponse<MasAssetStatusResponse> create(MasAssetStatusRequest req);
    ApiResponse<MasAssetStatusResponse> updateById(Long id,MasAssetStatusRequest req);
    ApiResponse<MasAssetStatusResponse> changeStatusById(Long id,String status);
    ApiResponse<MasAssetStatusResponse> getById(Long id);
    ApiResponse<List<MasAssetStatusResponse>> getAll(Integer flag);
}

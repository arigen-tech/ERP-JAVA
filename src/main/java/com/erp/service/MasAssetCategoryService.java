package com.erp.service;

import com.erp.request.MasAssetCategoryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasAssetCategoryResponse;

import java.util.List;

public interface MasAssetCategoryService {

    ApiResponse<MasAssetCategoryResponse> create(MasAssetCategoryRequest req);
    ApiResponse<MasAssetCategoryResponse> updateById(Long id,MasAssetCategoryRequest req);
    ApiResponse<MasAssetCategoryResponse> changeStatusById(Long id,String status);
    ApiResponse<MasAssetCategoryResponse> getById(Long id);
    ApiResponse<List<MasAssetCategoryResponse>> getAll(Integer flag);
}

package com.erp.service;

import com.erp.request.MasCategoryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCategoryResponse;

import java.util.List;

public interface MasCategoryService {

    ApiResponse<MasCategoryResponse> create(MasCategoryRequest req);
    ApiResponse<MasCategoryResponse> updateById(Long id,MasCategoryRequest req);
    ApiResponse<MasCategoryResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCategoryResponse> getById(Long id);
    ApiResponse<List<MasCategoryResponse>> getAll(Integer flag);
}

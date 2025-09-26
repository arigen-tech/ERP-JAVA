package com.erp.service;

import com.erp.entity.MasStoreItemClass;
import com.erp.request.MasStoreItemClassRequest;
import com.erp.response.ApiResponse;

import java.util.List;

public interface MasStoreItemClassService {
    ApiResponse<MasStoreItemClass> create(MasStoreItemClassRequest request);

    ApiResponse<MasStoreItemClass> updateById(Long id, MasStoreItemClassRequest request);

    ApiResponse<MasStoreItemClass> changeStatusById(Long id, String status);

    ApiResponse<MasStoreItemClass> getById(Long id);

    ApiResponse<List<MasStoreItemClass>> getAll(Integer flag);
}

package com.erp.service;

import com.erp.entity.MasGender;
import com.erp.request.MasGenderRequest;
import com.erp.response.ApiResponse;

import java.util.List;

public interface MasGenderService {
    ApiResponse<MasGender> create(MasGenderRequest request);

    ApiResponse<MasGender> updateById(Long id, MasGenderRequest request);

    ApiResponse<MasGender> changeStatusById(Long id, String status);

    ApiResponse<MasGender> getById(Long id);

    ApiResponse<List<MasGender>> getAll(Integer flag);
}

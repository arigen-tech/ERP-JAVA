package com.erp.service;

import com.erp.request.MasQualificationRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasQualificationResponse;

import java.util.List;

public interface MasQualificationService {

    ApiResponse<MasQualificationResponse> add(MasQualificationRequest request);
    ApiResponse<MasQualificationResponse> changeStatusById(Long id,String status);
    ApiResponse<MasQualificationResponse> updateById(Long id, MasQualificationRequest req);
    ApiResponse<MasQualificationResponse> getById(Long id);
    ApiResponse<List<MasQualificationResponse>> getAll(Integer flag);

}

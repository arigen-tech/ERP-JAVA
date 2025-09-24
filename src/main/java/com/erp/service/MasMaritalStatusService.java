package com.erp.service;

import com.erp.request.MasMaritalStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasMaritalStatusResponse;

import java.util.List;

public interface MasMaritalStatusService {

    ApiResponse<MasMaritalStatusResponse> addMaritalStatus(MasMaritalStatusRequest request);
    ApiResponse<MasMaritalStatusResponse> changeMaritalStatus(Long id, String status);
    ApiResponse<MasMaritalStatusResponse> editMaritalStatus(Long id, MasMaritalStatusRequest request);
    ApiResponse<MasMaritalStatusResponse> getMaritalStatusById(Long id);
    ApiResponse<List<MasMaritalStatusResponse>> getAllMaritalStatuses(Integer flag);
}

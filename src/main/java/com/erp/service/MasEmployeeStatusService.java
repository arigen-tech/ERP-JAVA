package com.erp.service;

import com.erp.request.MasEmployeeStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasEmployeeStatusResponse;

import java.util.List;

public interface MasEmployeeStatusService {

    ApiResponse<MasEmployeeStatusResponse> create(MasEmployeeStatusRequest req);
    ApiResponse<MasEmployeeStatusResponse> updateById(Long id,MasEmployeeStatusRequest req);
    ApiResponse<MasEmployeeStatusResponse> changeStatusById(Long id,String status);
    ApiResponse<MasEmployeeStatusResponse> getById(Long id);
    ApiResponse<List<MasEmployeeStatusResponse>> getAll(Integer flag);
}

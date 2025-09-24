package com.erp.service;

import com.erp.request.MasLeaveTypeRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasLeaveTypeResponse;

import java.util.List;

public interface MasLeaveTypeService {

    ApiResponse<MasLeaveTypeResponse> create(MasLeaveTypeRequest req);
    ApiResponse<MasLeaveTypeResponse> updateById(Long id,MasLeaveTypeRequest req);
    ApiResponse<MasLeaveTypeResponse> changeStatusById(Long id,String status);
    ApiResponse<MasLeaveTypeResponse> getById(Long id);
    ApiResponse<List<MasLeaveTypeResponse>> getAll(Integer flag);
}

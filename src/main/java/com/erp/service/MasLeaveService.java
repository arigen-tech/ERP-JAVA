package com.erp.service;

import com.erp.request.MasLeaveRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasLeaveResponse;

import java.util.List;

public interface MasLeaveService {

    public ApiResponse<List<MasLeaveResponse>> getAll(Integer flag);
    public ApiResponse<MasLeaveResponse> changeStatusById(Long id, String status);
    public ApiResponse<MasLeaveResponse> findById(Long id);
    public ApiResponse<MasLeaveResponse> add(MasLeaveRequest request);
    public ApiResponse<MasLeaveResponse> updateById(Long id, MasLeaveRequest request);
}

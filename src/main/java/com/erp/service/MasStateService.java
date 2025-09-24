package com.erp.service;

import com.erp.request.MasStateRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasStateResponse;

import java.util.List;

public interface MasStateService {

    ApiResponse<MasStateResponse> create(MasStateRequest req);
    ApiResponse<MasStateResponse> updateById(Long id,MasStateRequest req);
    ApiResponse<MasStateResponse> changeStatusById(Long id,String status);
    ApiResponse<MasStateResponse> getById(Long id);
    ApiResponse<List<MasStateResponse>> getAll(Integer flag);
}

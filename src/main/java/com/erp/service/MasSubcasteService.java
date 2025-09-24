package com.erp.service;

import com.erp.request.MasSubcasteRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasSubcasteResponse;

import java.util.List;

public interface MasSubcasteService {

    ApiResponse<MasSubcasteResponse> create(MasSubcasteRequest req);
    ApiResponse<MasSubcasteResponse> updateById(Long id,MasSubcasteRequest req);
    ApiResponse<MasSubcasteResponse> changeStatusById(Long id,String status);
    ApiResponse<MasSubcasteResponse> getById(Long id);
    ApiResponse<List<MasSubcasteResponse>> getAll(Integer flag);
}

package com.erp.service;

import com.erp.request.MasCentreRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCentreResponse;

import java.util.List;

public interface MasCentreService {

    ApiResponse<MasCentreResponse> create(MasCentreRequest req);
    ApiResponse<MasCentreResponse> updateById(Long id,MasCentreRequest req);
    ApiResponse<MasCentreResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCentreResponse> getById(Long id);
    ApiResponse<List<MasCentreResponse>> getAll(Integer flag);
}

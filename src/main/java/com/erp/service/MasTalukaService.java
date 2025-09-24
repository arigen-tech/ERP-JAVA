package com.erp.service;

import com.erp.request.MasTalukaRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasTalukaResponse;

import java.util.List;

public interface MasTalukaService {

    ApiResponse<MasTalukaResponse> create(MasTalukaRequest req);
    ApiResponse<MasTalukaResponse> updateById(Long id,MasTalukaRequest req);
    ApiResponse<MasTalukaResponse> changeStatusById(Long id,String status);
    ApiResponse<MasTalukaResponse> getById(Long id);
    ApiResponse<List<MasTalukaResponse>> getAll(Integer flag);
}

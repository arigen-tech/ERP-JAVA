package com.erp.service;

import com.erp.request.MasTitleRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasTitleResponse;

import java.util.List;

public interface MasTitleService {

    ApiResponse<MasTitleResponse> create(MasTitleRequest req);
    ApiResponse<MasTitleResponse> updateById(Long id,MasTitleRequest req);
    ApiResponse<MasTitleResponse> changeStatusById(Long id,String status);
    ApiResponse<MasTitleResponse> getById(Long id);
    ApiResponse<List<MasTitleResponse>> getAll(Integer flag);
}

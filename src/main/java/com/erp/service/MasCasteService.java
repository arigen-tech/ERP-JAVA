package com.erp.service;

import com.erp.request.MasCasteRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCasteResponse;

import java.util.List;

public interface MasCasteService {

    ApiResponse<MasCasteResponse> create(MasCasteRequest req);
    ApiResponse<MasCasteResponse> updateById(Long id,MasCasteRequest req);
    ApiResponse<MasCasteResponse> changeStatusById(Long id,String status);
    ApiResponse<MasCasteResponse> getById(Long id);
    ApiResponse<List<MasCasteResponse>> getAll(Integer flag);
}

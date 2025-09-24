package com.erp.service;

import com.erp.request.MasGradeRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasGradeResponse;

import java.util.List;

public interface MasGradeService {

    ApiResponse<MasGradeResponse> create(MasGradeRequest req);
    ApiResponse<MasGradeResponse> updateById(Long id,MasGradeRequest req);
    ApiResponse<MasGradeResponse> changeStatusById(Long id,String status);
    ApiResponse<MasGradeResponse> getById(Long id);
    ApiResponse<List<MasGradeResponse>> getAll(Integer flag);
}

package com.erp.service;

import com.erp.request.MasCourseRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCourseResponse;

import java.util.List;

public interface MasCourseService {

    ApiResponse<MasCourseResponse> create(MasCourseRequest request);
    ApiResponse<MasCourseResponse> updateById(Long id,MasCourseRequest request);
    ApiResponse<MasCourseResponse> changeStatusById(Long id, String status);
    ApiResponse<MasCourseResponse> getById(Long id);
    ApiResponse<List<MasCourseResponse>> getAll(Integer flag);

}

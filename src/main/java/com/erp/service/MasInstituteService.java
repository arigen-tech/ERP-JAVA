package com.erp.service;

import com.erp.request.MasInstituteRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasInstituteResponse;

import java.util.List;

public interface MasInstituteService {

    ApiResponse<MasInstituteResponse> add(MasInstituteRequest req);
    ApiResponse<MasInstituteResponse> updateById(Long id, MasInstituteRequest req);
    ApiResponse<MasInstituteResponse> changeStatusById(Long id,String status);
    ApiResponse<MasInstituteResponse> getById(Long id);
    ApiResponse<List<MasInstituteResponse>> getAll(Integer flag);
}

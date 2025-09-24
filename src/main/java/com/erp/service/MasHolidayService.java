package com.erp.service;

import com.erp.request.MasHolidayRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasHolidayResponse;

import java.util.List;

public interface MasHolidayService {

    ApiResponse<MasHolidayResponse> create(MasHolidayRequest req);
    ApiResponse<MasHolidayResponse> updateById(Long id,MasHolidayRequest req);
    ApiResponse<MasHolidayResponse> changeStatusById(Long id,String status);
    ApiResponse<MasHolidayResponse> getById(Long id);
    ApiResponse<List<MasHolidayResponse>> getAll(Integer flag);
}

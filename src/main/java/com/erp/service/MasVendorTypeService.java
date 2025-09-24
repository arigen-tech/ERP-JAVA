package com.erp.service;

import com.erp.request.MasVendorTypeReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasVendorTypeRes;

import java.util.List;

public interface MasVendorTypeService {

    ApiResponse<MasVendorTypeRes> create(MasVendorTypeReq req);
    ApiResponse<MasVendorTypeRes> updateById(Long id,MasVendorTypeReq req);
    ApiResponse<MasVendorTypeRes> changeStatusById(Long id,String status);
    ApiResponse<MasVendorTypeRes> getById(Long id);
    ApiResponse<List<MasVendorTypeRes>> getAll(Integer flag);
}

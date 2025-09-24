package com.erp.service;

import com.erp.request.MasExternalAgencyTypeReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasExternalAgencyTypeRes;

import java.util.List;

public interface MasExternalAgencyTypeService {

    ApiResponse<MasExternalAgencyTypeRes> create(MasExternalAgencyTypeReq req);
    ApiResponse<MasExternalAgencyTypeRes> updateById(Long id,MasExternalAgencyTypeReq req);
    ApiResponse<MasExternalAgencyTypeRes> changeStatusById(Long id,String status);
    ApiResponse<MasExternalAgencyTypeRes> getById(Long id);
    ApiResponse<List<MasExternalAgencyTypeRes>> getAll(Integer flag);
}

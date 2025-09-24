package com.erp.service;

import com.erp.request.MasStoreItemGrpReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasStoreItemGrpRes;

import java.util.List;

public interface MasStoreItemGroupService {

    ApiResponse<MasStoreItemGrpRes> create(MasStoreItemGrpReq req);
    ApiResponse<MasStoreItemGrpRes> updateById(Long id,MasStoreItemGrpReq req);
    ApiResponse<MasStoreItemGrpRes> changeStatusById(Long id,String status);
    ApiResponse<MasStoreItemGrpRes> getById(Long id);
    ApiResponse<List<MasStoreItemGrpRes>> getAll(Integer flag);
}

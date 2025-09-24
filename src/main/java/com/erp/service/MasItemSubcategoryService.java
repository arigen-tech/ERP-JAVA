package com.erp.service;

import com.erp.request.MasItemSubcategoryReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasItemSubcategoryRes;

import java.util.List;

public interface MasItemSubcategoryService {
    ApiResponse<MasItemSubcategoryRes> create(MasItemSubcategoryReq req);
    ApiResponse<MasItemSubcategoryRes> updateById(Long id,MasItemSubcategoryReq req);
    ApiResponse<MasItemSubcategoryRes> changeStatusById(Long id,String status);
    ApiResponse<MasItemSubcategoryRes> getById(Long id);
    ApiResponse<List<MasItemSubcategoryRes>> getAll(Integer flag);

}

package com.erp.service;

import com.erp.request.MasItemCategoryReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasItemCategoryRes;

import java.util.List;

public interface MasItemCategoryService {

    ApiResponse<MasItemCategoryRes> create(MasItemCategoryReq req);
    ApiResponse<MasItemCategoryRes> updateById(Long id,MasItemCategoryReq req);
    ApiResponse<MasItemCategoryRes> changeStatusById(Long id,String status);
    ApiResponse<MasItemCategoryRes> getById(Long id);
    ApiResponse<List<MasItemCategoryRes>> getAll(Integer flag);

}

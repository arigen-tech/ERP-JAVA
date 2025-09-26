package com.erp.service;

import com.erp.entity.MasUserGroup;
import com.erp.request.MasUserGroupRequest;
import com.erp.response.ApiResponse;

import java.util.List;

public interface MasUserGroupService {
    ApiResponse<MasUserGroup> create(MasUserGroupRequest request);

    ApiResponse<MasUserGroup> updateById(Long id, MasUserGroupRequest request);

    ApiResponse<MasUserGroup> changeStatusById(Long id, String status);

    ApiResponse<MasUserGroup> getById(Long id);

    ApiResponse<List<MasUserGroup>> getAll(Integer flag);
}

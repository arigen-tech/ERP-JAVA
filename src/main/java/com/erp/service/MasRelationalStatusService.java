package com.erp.service;

import com.erp.request.MasRelationRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasRelationResponse;

import java.util.List;

public interface MasRelationalStatusService {

    public ApiResponse<List<MasRelationResponse>> getAllRelations(Integer flag);
    public ApiResponse<MasRelationResponse> changeRelationsStatus(Long id, String status);
    public ApiResponse<MasRelationResponse> findById(Long id);
    public ApiResponse<MasRelationResponse> addRelation(MasRelationRequest relationRequest);
    public ApiResponse<MasRelationResponse> updateRelation(Long id, MasRelationRequest relationRequest);
}

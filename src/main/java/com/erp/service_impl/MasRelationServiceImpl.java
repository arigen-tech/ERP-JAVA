package com.erp.service_impl;
import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasRelation;
import com.erp.repo.MasRelationRepo;
import com.erp.request.MasRelationRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasRelationResponse;
import com.erp.service.MasRelationalStatusService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasRelationServiceImpl implements MasRelationalStatusService {

    @Autowired
    private MasRelationRepo repo;

    private  final Logger LOGGER=LoggerFactory.getLogger(MasRelationServiceImpl.class);

    @Override
    public ApiResponse<List<MasRelationResponse>> getAllRelations(Integer flag) {
        try {

            List<MasRelation> statues ;
            if(flag==1){
                statues=repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return ResponseUtils.createFailureResponse(null, new TypeReference<List<MasRelationResponse>>() {}, FailureResponseMsg.FLAG_NOT_FOUND, 400);
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()),new TypeReference<>(){});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasRelationResponse> changeRelationsStatus(Long id, String status) {
        try {

            Optional<MasRelation> byId = repo.findById(id);
            if(byId.isPresent()){
                MasRelation entity = byId.get();
                entity.setStatus(status);
//                entity.setUpdatedBy();
                MasRelation save = repo.save(entity);
                return ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<>(){});
            }
            else{
                return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELATION_NOT_FOUND,404);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasRelationResponse> findById(Long id) {
        try {

            Optional<MasRelation> byId = repo.findById(id);
            if(byId.isPresent()){
                MasRelation entity = byId.get();
                return  ResponseUtils.createSuccessResponse(mapToResponse(entity), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELATION_NOT_FOUND,404);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasRelationResponse> addRelation(MasRelationRequest relationRequest) {
        try {
            MasRelation entity=new MasRelation();
            entity.setStatusCode(relationRequest.getStatusCode());
            entity.setStatusName(relationRequest.getStatusName());
            //            entity.setUpdatedBy();
            entity.setStatus("y");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasRelationResponse> updateRelation(Long id, MasRelationRequest relationRequest) {
        try {

            Optional<MasRelation> byId = repo.findById(id);
            if(byId.isPresent()){
                MasRelation entity = byId.get();
                entity.setStatusCode(relationRequest.getStatusCode());
                entity.setStatusName(relationRequest.getStatusName());
//            entity.setUpdatedBy();
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)),new TypeReference<>(){});
            }else{
                return ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELATION_NOT_FOUND,404);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR , HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    private MasRelationResponse mapToResponse(MasRelation entity){
        MasRelationResponse response = new MasRelationResponse();
        response.setId(entity.getId());
        response.setStatusCode(entity.getStatusCode());
        response.setStatusName(entity.getStatusName());
        response.setStatus(entity.getStatus());
        return response;
    }
}

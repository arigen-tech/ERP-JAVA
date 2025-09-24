package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasAssetStatus;
import com.erp.repo.MasAssetStatusRepo;
import com.erp.request.MasAssetStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasAssetStatusResponse;
import com.erp.service.MasAssetStatusService;
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
public class MasAssetStatusServiceImpl implements MasAssetStatusService {


    @Autowired
    private MasAssetStatusRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasAssetStatusServiceImpl.class);

    @Override
    public ApiResponse<MasAssetStatusResponse> create(MasAssetStatusRequest request) {
        try{

            MasAssetStatus entity= new MasAssetStatus();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setDescription(request.getDescription());
            entity.setStatus("y");
//        entity.setCreatedBy("");
//        entity.setUpdatedBy("");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasAssetStatusResponse> updateById(Long id, MasAssetStatusRequest request) {
        try{

            Optional<MasAssetStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                MasAssetStatus entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
                entity.setDescription(request.getDescription());
//          entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasAssetStatusResponse> changeStatusById(Long id, String status) {
        try{

            Optional<MasAssetStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                MasAssetStatus entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasAssetStatusResponse> getById(Long id) {
        try{

            Optional<MasAssetStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasAssetStatusResponse>> getAll(Integer flag) {
        try{

            List<MasAssetStatus> statues;
            if(flag==1){
                statues=repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasAssetStatusResponse mapToResponse(MasAssetStatus entity){
        MasAssetStatusResponse response = new MasAssetStatusResponse();

        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());

        return  response;
    }

}

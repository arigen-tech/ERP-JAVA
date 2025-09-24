package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasAssetCategory;
import com.erp.repo.MasAssetCategoryRepo;
import com.erp.request.MasAssetCategoryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasAssetCategoryResponse;
import com.erp.service.MasAssetCategoryService;
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
public class MasAssetCategoryServiceImpl implements MasAssetCategoryService {

    @Autowired
    private MasAssetCategoryRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasAssetCategoryServiceImpl.class);


    @Override
    public ApiResponse<MasAssetCategoryResponse> create(MasAssetCategoryRequest request) {
        try{

            MasAssetCategory entity= new MasAssetCategory();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setType(request.getType());
            entity.setDescription(request.getDescription());
            entity.setRemarks(request.getRemarks());
            entity.setDepreciation(request.getDepreciation());
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
    public ApiResponse<MasAssetCategoryResponse> updateById(Long id, MasAssetCategoryRequest request) {
        try{

            Optional<MasAssetCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                MasAssetCategory entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
                entity.setType(request.getType());
                entity.setDescription(request.getDescription());
                entity.setRemarks(request.getRemarks());
                entity.setDepreciation(request.getDepreciation());
//          entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasAssetCategoryResponse> changeStatusById(Long id, String status) {
        try{

            Optional<MasAssetCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                MasAssetCategory entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasAssetCategoryResponse> getById(Long id) {
        try{

            Optional<MasAssetCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ASSET_CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasAssetCategoryResponse>> getAll(Integer flag) {
        try{

            List<MasAssetCategory> statues;
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

    private MasAssetCategoryResponse mapToResponse(MasAssetCategory entity){
        MasAssetCategoryResponse response = new MasAssetCategoryResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setType(entity.getType());
        response.setDepreciation(entity.getDepreciation());
        response.setDescription(entity.getDescription());
        response.setRemarks(entity.getRemarks());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

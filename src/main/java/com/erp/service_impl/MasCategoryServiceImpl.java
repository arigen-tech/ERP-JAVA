package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCategory;
import com.erp.repo.MasCategoryRepo;
import com.erp.request.MasCategoryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCategoryResponse;
import com.erp.service.MasCategoryService;
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
public class MasCategoryServiceImpl implements MasCategoryService {

    @Autowired
    private MasCategoryRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasCategoryServiceImpl.class);

    @Override
    public ApiResponse<MasCategoryResponse> create(MasCategoryRequest request) {
        try {

            MasCategory entity= new MasCategory();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//            entity.setCreatedBy("");
//        entity.setUpdatedBy("");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCategoryResponse> updateById(Long id, MasCategoryRequest request) {
        try {

            Optional<MasCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                MasCategory entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//          entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCategoryResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                MasCategory entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCategoryResponse> getById(Long id) {
        try {

            Optional<MasCategory> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CATEGORY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasCategoryResponse>> getAll(Integer flag) {
        try {

            List<MasCategory> statues;
            if(flag==1){
                statues=repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasCategoryResponse mapToResponse(MasCategory entity){
        MasCategoryResponse response = new MasCategoryResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());

        return  response;
    }
}

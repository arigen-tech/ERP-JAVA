package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasExternalAgencyType;
import com.erp.repo.MasExternalAgencyTypeRepo;
import com.erp.request.MasExternalAgencyTypeReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasExternalAgencyTypeRes;
import com.erp.service.MasExternalAgencyTypeService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasExternalAgencyTypeServiceImpl implements MasExternalAgencyTypeService {


    @Autowired
    private MasExternalAgencyTypeRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasExternalAgencyTypeServiceImpl.class);

    @Override
    public ApiResponse<MasExternalAgencyTypeRes> create(MasExternalAgencyTypeReq request) {
        try {

            MasExternalAgencyType entity= new MasExternalAgencyType();
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
    public ApiResponse<MasExternalAgencyTypeRes> updateById(Long id, MasExternalAgencyTypeReq request) {
        try {

            Optional<MasExternalAgencyType> byId = repo.findById(id);
            if(byId.isPresent()){
                MasExternalAgencyType entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//          entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EX_AGENCY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasExternalAgencyTypeRes> changeStatusById(Long id, String status) {
        try {

            Optional<MasExternalAgencyType> byId = repo.findById(id);
            if(byId.isPresent()){
                MasExternalAgencyType entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EX_AGENCY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasExternalAgencyTypeRes> getById(Long id) {
        try {

            Optional<MasExternalAgencyType> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EX_AGENCY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasExternalAgencyTypeRes>> getAll(Integer flag) {
        try {

            List<MasExternalAgencyType> statues;
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

    private MasExternalAgencyTypeRes mapToResponse(MasExternalAgencyType entity){
        MasExternalAgencyTypeRes response = new MasExternalAgencyTypeRes();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());

        return response;
    }
}

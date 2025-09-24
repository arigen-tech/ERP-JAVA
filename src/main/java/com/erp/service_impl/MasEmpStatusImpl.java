package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasEmployeeStatus;
import com.erp.repo.MasEmployeeStatusRepo;
import com.erp.request.MasEmployeeStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasEmployeeStatusResponse;
import com.erp.service.MasEmployeeStatusService;
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
public class MasEmpStatusImpl implements MasEmployeeStatusService {

    @Autowired
    private MasEmployeeStatusRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasEmpStatusImpl.class);

    @Override
    public ApiResponse<MasEmployeeStatusResponse> create(MasEmployeeStatusRequest request) {
        try {

            MasEmployeeStatus entity= new MasEmployeeStatus();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//        entity.setUpdatedBy("");
//            entity.setCreatedBy("");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasEmployeeStatusResponse> updateById(Long id, MasEmployeeStatusRequest request) {
        try {

            Optional<MasEmployeeStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                MasEmployeeStatus entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//          entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EMP_STATUS_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasEmployeeStatusResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasEmployeeStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                MasEmployeeStatus entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EMP_STATUS_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasEmployeeStatusResponse> getById(Long id) {
        try {

            Optional<MasEmployeeStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.EMP_STATUS_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasEmployeeStatusResponse>> getAll(Integer flag) {
        try {

            List<MasEmployeeStatus> statues;
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

    private MasEmployeeStatusResponse mapToResponse(MasEmployeeStatus entity){
        MasEmployeeStatusResponse response = new MasEmployeeStatusResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());

        return response;
    }
}

package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasQualification;
import com.erp.repo.MasQualificationRepo;
import com.erp.request.MasQualificationRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasQualificationResponse;
import com.erp.service.MasQualificationService;
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
public class MasQualificationServiceImpl implements MasQualificationService {

    @Autowired
    private MasQualificationRepo repo;

    private  final Logger LOGGER= LoggerFactory.getLogger(MasQualificationServiceImpl.class);

    @Override
    public ApiResponse<MasQualificationResponse> add(MasQualificationRequest request) {

        try {
            MasQualification entity = new MasQualification();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
//            entity.setCentreId();
//            entity.setUpdatedBy("");
            entity.setStatus("y");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<MasQualificationResponse>() {});
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasQualificationResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasQualification> byId = repo.findById(id);
            if(byId.isPresent()){
                MasQualification entity = byId.get();
                entity.setStatus(status);
//            entity.setUpdatedBy("");
                MasQualification save = repo.save(entity);
                return  ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasQualificationResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.QUALIFICATION_NOT_FOUND,404);
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR,HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasQualificationResponse> updateById(Long id, MasQualificationRequest req) {
        try {
            Optional<MasQualification> byId = repo.findById(id);
            if(byId.isPresent()){
                MasQualification entity = byId.get();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
//              entity.setUpdatedBy("");
                MasQualification save = repo.save(entity);
                return  ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasQualificationResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.QUALIFICATION_NOT_FOUND,404);
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasQualificationResponse> getById(Long id) {
        try {
            Optional<MasQualification> byId = repo.findById(id);
            if(byId.isPresent()){
                return ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<MasQualificationResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.QUALIFICATION_NOT_FOUND,404);
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasQualificationResponse>> getAll(Integer flag) {
        try {
            List<MasQualification> statuses;
            if (flag==1){
                statuses = repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statuses=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<List<MasQualificationResponse>>() {}, FailureResponseMsg.FLAG_NOT_FOUND, 400);
            }
            return ResponseUtils.createSuccessResponse(statuses.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<List<MasQualificationResponse>>() {});
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasQualificationResponse mapToResponse(MasQualification entity){
        MasQualificationResponse response = new MasQualificationResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return response;
    }
}

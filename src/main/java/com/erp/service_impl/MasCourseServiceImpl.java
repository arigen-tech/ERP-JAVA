package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCourse;
import com.erp.repo.MasCourseRepo;
import com.erp.request.MasCourseRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCourseResponse;
import com.erp.service.MasCourseService;
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
public class MasCourseServiceImpl implements MasCourseService {

    @Autowired
    private MasCourseRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasCourseServiceImpl.class);

    @Override
    public ApiResponse<MasCourseResponse> create(MasCourseRequest request) {
        try {
            MasCourse entity= new MasCourse();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//            entity.setUpdatedBy();
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<MasCourseResponse>() {});
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCourseResponse> updateById(Long id, MasCourseRequest request) {
        try {
            Optional<MasCourse> byId = repo.findById(id);
            if(byId.isPresent()){
                MasCourse entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//                entity.setUpdatedBy();
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<MasCourseResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COURSE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCourseResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasCourse> byId = repo.findById(id);
            if(byId.isPresent()){
                MasCourse entity = byId.get();
                entity.setStatus(status);
//                entity.setUpdatedBy();
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<MasCourseResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COURSE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCourseResponse> getById(Long id) {
        try {
            Optional<MasCourse> byId = repo.findById(id);
            if(byId.isPresent()){
                return ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<MasCourseResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COURSE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasCourseResponse>> getAll(Integer flag) {
        try {
            List<MasCourse> statuses;
            if(flag==1){
                statuses=repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statuses=repo.findByStatusInIgnoreCase(List.of("y", "n"));
            }else{
                return ResponseUtils.createFailureResponse(null, new TypeReference<List<MasCourseResponse>>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statuses.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<List<MasCourseResponse>>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasCourseResponse mapToResponse(MasCourse entity){
        MasCourseResponse response= new MasCourseResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return response;
    }
}

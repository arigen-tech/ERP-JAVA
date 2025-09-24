package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasGrade;
import com.erp.repo.MasGradeRepo;
import com.erp.request.MasGradeRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasGradeResponse;
import com.erp.service.MasGradeService;
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
public class MasGradeServiceImpl implements MasGradeService {

    @Autowired
    private MasGradeRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasGradeServiceImpl.class);


    @Override
    public ApiResponse<MasGradeResponse> create(MasGradeRequest req) {
        try {
            MasGrade entity= new MasGrade();
            entity.setCode(req.getCode());
            entity.setName(req.getName());
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
    public ApiResponse<MasGradeResponse> updateById(Long id, MasGradeRequest req) {
        try {

            Optional<MasGrade> byId = repo.findById(id);
            if(byId.isPresent()){
                MasGrade entity = byId.get();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.GRADE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasGradeResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasGrade> byId = repo.findById(id);
            if(byId.isPresent()){
                MasGrade entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.GRADE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasGradeResponse> getById(Long id) {
        try {

            Optional<MasGrade> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.GRADE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasGradeResponse>> getAll(Integer flag) {
        try {

            List<MasGrade> statues;
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

    private MasGradeResponse mapToResponse(MasGrade entity){
        MasGradeResponse response= new MasGradeResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

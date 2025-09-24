package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasReligion;
import com.erp.repo.MasReligionRepo;
import com.erp.request.MasReligionRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasReligionResponse;
import com.erp.service.MasReligionService;
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
public class MasReligionServiceImpl implements MasReligionService {

    @Autowired
    private MasReligionRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasReligionServiceImpl.class);

    @Override
    public ApiResponse<MasReligionResponse> create(MasReligionRequest request) {

        try{

            MasReligion entity= new MasReligion();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//        entity.setUpdatedBy("");
            MasReligion save = repo.save(entity);
            return ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasReligionResponse>() {});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasReligionResponse> updateById(Long id, MasReligionRequest request) {

        try{
            Optional<MasReligion> byId = repo.findById(id);
            if(byId.isPresent()){
                MasReligion entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//            entity.setUpdatedBy("");
                MasReligion save = repo.save(entity);
                return  ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasReligionResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELIGION_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasReligionResponse> changeStatusById(Long id, String status) {

        try{
            Optional<MasReligion> byId = repo.findById(id);
            if(byId.isPresent()){
                MasReligion entity = byId.get();
                entity.setStatus(status);
//            entity.setUpdatedBy("");
                MasReligion save = repo.save(entity);
                return  ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasReligionResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELIGION_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasReligionResponse> getById(Long id) {
        try{
            Optional<MasReligion> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<MasReligionResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.RELIGION_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasReligionResponse>> getAll(Integer flag) {
        try{
            List<MasReligion> statues;
            if(flag==1){
                statues=repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<List<MasReligionResponse>>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<List<MasReligionResponse>>() {});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasReligionResponse mapToResponse(MasReligion entity){
        MasReligionResponse response= new MasReligionResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

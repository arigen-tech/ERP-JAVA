package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasInstitute;
import com.erp.repo.MasInstituteRepo;
import com.erp.request.MasInstituteRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasInstituteResponse;
import com.erp.service.MasInstituteService;
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
public class MasInstituteServiceImpl implements MasInstituteService {

    @Autowired
    private MasInstituteRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasInstituteServiceImpl.class);

    @Override
    public ApiResponse<MasInstituteResponse> add(MasInstituteRequest request) {
        try {
            MasInstitute entity = new MasInstitute();
            entity.setCode(request.getCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//            entity.setUpdatedBy();
            return ResponseUtils.createSuccessResponse(mapToResponse( repo.save(entity)), new TypeReference<MasInstituteResponse>() {});
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasInstituteResponse> updateById(Long id, MasInstituteRequest request) {
        try {
            Optional<MasInstitute> byId = repo.findById(id);
            if(byId.isPresent()){
                MasInstitute entity = byId.get();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
//                entity.setUpdatedBy();
                return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<MasInstituteResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.INSTITUTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasInstituteResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasInstitute> byId = repo.findById(id);
            if(byId.isPresent()){
                MasInstitute entity = byId.get();
                entity.setStatus(status);
//                entity.setUpdatedBy();
                MasInstitute save = repo.save(entity);
                return  ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<MasInstituteResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.INSTITUTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasInstituteResponse> getById(Long id) {
        try {
            Optional<MasInstitute> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<MasInstituteResponse>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.INSTITUTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasInstituteResponse>> getAll(Integer flag) {
        try {
            List<MasInstitute> statuses;
            if (flag==1){
                statuses = repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statuses=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<List<MasInstituteResponse>>() {}, FailureResponseMsg.FLAG_NOT_FOUND, HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statuses.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<List<MasInstituteResponse>>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasInstituteResponse mapToResponse(MasInstitute entity){
        MasInstituteResponse response = new MasInstituteResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

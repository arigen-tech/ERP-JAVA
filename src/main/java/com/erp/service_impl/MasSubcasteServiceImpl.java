package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCaste;
import com.erp.entity.MasSubcaste;
import com.erp.repo.MasCasteRepo;
import com.erp.repo.MasSubcasteRepo;
import com.erp.request.MasSubcasteRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasSubcasteResponse;
import com.erp.service.MasSubcasteService;
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
public class MasSubcasteServiceImpl implements MasSubcasteService {


    @Autowired
    private MasCasteRepo casteRepo;

    @Autowired
    private MasSubcasteRepo subcasteRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasSubcasteServiceImpl.class);


    @Override
    public ApiResponse<MasSubcasteResponse> create(MasSubcasteRequest req) {
        try {

            Optional<MasCaste> byId = casteRepo.findById(req.getCasteId());
            if(byId.isPresent()){
                MasSubcaste entity=new MasSubcaste();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//            entity.setUpdatedBy("");
                entity.setCaste(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(subcasteRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CASTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasSubcasteResponse> updateById(Long id, MasSubcasteRequest req) {
        try {

            Optional<MasSubcaste> subcasteOpt = subcasteRepo.findById(id);
            if(subcasteOpt.isPresent()){
                Optional<MasCaste> casteOpt = casteRepo.findById(req.getCasteId());
                if(casteOpt.isPresent()){
                    MasSubcaste entity = subcasteOpt.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
//                entity.setUpdatedBy("");
                    entity.setCaste(casteOpt.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(subcasteRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.CASTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.SUBCASTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @Override
    public ApiResponse<MasSubcasteResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasSubcaste> byId = subcasteRepo.findById(id);
            if(byId.isPresent()){
                MasSubcaste entity = byId.get();
                entity.setStatus(status);
//            entity.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(subcasteRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.SUBCASTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasSubcasteResponse> getById(Long id) {
        try {

            Optional<MasSubcaste> byId = subcasteRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.SUBCASTE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasSubcasteResponse>> getAll(Integer flag) {
        try {

            List<MasSubcaste> statues;
            if(flag==1){
                statues=subcasteRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=subcasteRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasSubcasteResponse mapToResponse(MasSubcaste entity){
        MasSubcasteResponse response= new MasSubcasteResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        response.setCasteId(entity.getCaste().getId());
        return  response;
    }
}

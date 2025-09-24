package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCountry;
import com.erp.entity.MasState;
import com.erp.repo.MasCountryRepo;
import com.erp.repo.MasStateRepo;
import com.erp.request.MasStateRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasStateResponse;
import com.erp.service.MasStateService;
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
public class MasStateServiceImpl implements MasStateService {

    @Autowired
    private MasCountryRepo countryRepo;

    @Autowired
    private MasStateRepo stateRepo;

    private final Logger LOGGER=LoggerFactory.getLogger(MasStateServiceImpl.class);

    @Override
    public ApiResponse<MasStateResponse> create(MasStateRequest req) {
        try {
            Optional<MasCountry> byId = countryRepo.findById(req.getCountryId());
            if(byId.isPresent()){
                MasState entity=new MasState();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                entity.setNameInKannada(req.getNameInKannada());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//            entity.setModifiedBy("");
                entity.setCountry(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(stateRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COUNTRY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasStateResponse> updateById(Long id, MasStateRequest req) {
        try {
            Optional<MasState> stateOpt = stateRepo.findById(id);
            if(stateOpt.isPresent()){
                Optional<MasCountry> countryOpt = countryRepo.findById(req.getCountryId());
                if(countryOpt.isPresent()){
                    MasState entity = stateOpt.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
                    entity.setNameInKannada(req.getNameInKannada());
//                entity.setModifiedBy("");
                    entity.setCountry(countryOpt.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(stateRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.COUNTRY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STATE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasStateResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasState> byId = stateRepo.findById(id);
            if(byId.isPresent()){
                MasState entity = byId.get();
                entity.setStatus(status);
//                entity.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(stateRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STATE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasStateResponse> getById(Long id) {
        try {

            Optional<MasState> byId = stateRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STATE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasStateResponse>> getAll(Integer flag) {
        try {

            List<MasState> statues;
            if(flag==1){
                statues=stateRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=stateRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasStateResponse mapToResponse(MasState entity){
         MasStateResponse response= new MasStateResponse();
            response.setId(entity.getId());
            response.setCode(entity.getCode());
            response.setName(entity.getName());
            response.setStatus(entity.getStatus());
            response.setCountryId(entity.getCountry().getId());
         return  response;
    }
}

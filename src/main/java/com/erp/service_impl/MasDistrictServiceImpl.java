package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasDistrict;
import com.erp.entity.MasState;
import com.erp.repo.MasDistrictRepo;
import com.erp.repo.MasStateRepo;
import com.erp.request.MasDistrictRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasDistrictResponse;
import com.erp.service.MasDistrictService;
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
public class MasDistrictServiceImpl implements MasDistrictService {

    @Autowired
    private MasStateRepo stateRepo;

    @Autowired
    private MasDistrictRepo districtRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasDistrictServiceImpl.class);

    @Override
    public ApiResponse<MasDistrictResponse> create(MasDistrictRequest req) {

        try {

            Optional<MasState> byId = stateRepo.findById(req.getStateId());
            if(byId.isPresent()){
                MasDistrict entity=new MasDistrict();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                entity.setNameInKannada(req.getNameInKannada());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//            entity.setModifiedBy("");
                entity.setState(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(districtRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STATE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasDistrictResponse> updateById(Long id, MasDistrictRequest req) {

        try {

            Optional<MasDistrict> distOpt = districtRepo.findById(id);
            if(distOpt.isPresent()){
                Optional<MasState> stateOpt = stateRepo.findById(req.getStateId());
                if(stateOpt.isPresent()){
                    MasDistrict entity = distOpt.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
                    entity.setNameInKannada(req.getNameInKannada());
//                entity.setModifiedBy("");
                    entity.setState(stateOpt.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(districtRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.STATE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasDistrictResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasDistrict> byId = districtRepo.findById(id);
            if(byId.isPresent()){
                MasDistrict entity = byId.get();
                entity.setStatus(status);
//                entity.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(districtRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasDistrictResponse> getById(Long id) {
        try {

            Optional<MasDistrict> byId = districtRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasDistrictResponse>> getAll(Integer flag) {
        try {

            List<MasDistrict> statues;
            if(flag==1){
                statues=districtRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=districtRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasDistrictResponse mapToResponse(MasDistrict entity){
        MasDistrictResponse response= new MasDistrictResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        response.setStateId(entity.getState().getId());
        return  response;
    }
}

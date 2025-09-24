package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasDistrict;
import com.erp.entity.MasTaluka;
import com.erp.repo.MasDistrictRepo;
import com.erp.repo.MasTalukaRepo;
import com.erp.request.MasTalukaRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasTalukaResponse;
import com.erp.service.MasTalukaService;
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
public class MasTalukaServiceImpl implements MasTalukaService {

    @Autowired
    private MasDistrictRepo districtRepo;

    @Autowired
    private MasTalukaRepo talukaRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasTalukaServiceImpl.class);

    @Override
    public ApiResponse<MasTalukaResponse> create(MasTalukaRequest req) {

        try {

            Optional<MasDistrict> byId = districtRepo.findById(req.getDistrictId());
            if(byId.isPresent()){
                MasTaluka entity=new MasTaluka();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                entity.setNameInKannada(req.getNameInKannada());
                entity.setStatus("y");
//                entity.setModifiedBy("");
//                entity.setCreatedBy("");
                entity.setDistrict(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(talukaRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @Override
    public ApiResponse<MasTalukaResponse> updateById(Long id, MasTalukaRequest req) {
        try {

            Optional<MasTaluka> talukaOpt = talukaRepo.findById(id);
            if(talukaOpt.isPresent()){
                Optional<MasDistrict> distOpt = districtRepo.findById(req.getDistrictId());
                if(distOpt.isPresent()){
                    MasTaluka entity = talukaOpt.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
                    entity.setNameInKannada(req.getNameInKannada());
//                entity.setUpdatedBy("");
                    entity.setDistrict(distOpt.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(talukaRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TALUKA_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasTalukaResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasTaluka> byId = talukaRepo.findById(id);
            if(byId.isPresent()){
                MasTaluka entity = byId.get();
                entity.setStatus(status);
                // entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(talukaRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TALUKA_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasTalukaResponse> getById(Long id) {
        try {

            Optional<MasTaluka> byId = talukaRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TALUKA_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasTalukaResponse>> getAll(Integer flag) {
        try {

            List<MasTaluka> statues;
            if(flag==1){
                statues=talukaRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=talukaRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,400);
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasTalukaResponse mapToResponse(MasTaluka entity) {

        MasTalukaResponse response = new MasTalukaResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        response.setDistrictId(entity.getDistrict().getId());
        return  response;
    }
}

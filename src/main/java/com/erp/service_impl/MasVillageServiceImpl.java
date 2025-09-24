package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasTaluka;
import com.erp.entity.MasVillage;
import com.erp.repo.MasTalukaRepo;
import com.erp.repo.MasVillageRepo;
import com.erp.request.MasVillageRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasVillageResponse;
import com.erp.service.MasVillageService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MasVillageServiceImpl implements MasVillageService {

    private final MasTalukaRepo talukaRepo;

    private final MasVillageRepo villageRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasVillageServiceImpl.class);

    @Override
    public ApiResponse<MasVillageResponse> create(MasVillageRequest request) {

        try {
            Optional<MasTaluka> byId = talukaRepo.findById(request.getTalukaId());
            if(byId.isPresent()){
                MasVillage entity=new MasVillage();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
                entity.setNameInKannada(request.getNameInKannada());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//            entity.setUpdatedBy("");
                entity.setTaluka(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(villageRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TALUKA_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasVillageResponse> updateById(Long id, MasVillageRequest req) {
        try {
            Optional<MasVillage> villageOpt = villageRepo.findById(id);
            if(villageOpt.isPresent()){
                Optional<MasTaluka> talukaOpt = talukaRepo.findById(req.getTalukaId());
                if(talukaOpt.isPresent()){
                    MasVillage entity = villageOpt.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
                    entity.setNameInKannada(req.getNameInKannada());
//                entity.setUpdatedBy("");
                    entity.setTaluka(talukaOpt.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(villageRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.TALUKA_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VILLAGE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasVillageResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasVillage> byId = villageRepo.findById(id);
            if(byId.isPresent()){
                MasVillage entity = byId.get();
                entity.setStatus(status);
                // entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(villageRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VILLAGE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasVillageResponse> getById(Long id) {
        try {
            Optional<MasVillage> byId = villageRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VILLAGE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasVillageResponse>> getAll(Integer flag) {
        try {
            List<MasVillage> statues;
            if(flag==1){
                statues=villageRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=villageRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasVillageResponse mapToResponse(MasVillage entity){
        MasVillageResponse response= new MasVillageResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setTalukaId(entity.getTaluka().getId());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

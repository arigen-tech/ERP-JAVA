package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCapacity;
import com.erp.entity.MasCentre;
import com.erp.entity.MasDistrict;
import com.erp.repo.MasCapacityRepo;
import com.erp.repo.MasCentreRepo;
import com.erp.repo.MasDistrictRepo;
import com.erp.request.MasCentreRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCentreResponse;
import com.erp.service.MasCentreService;
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
public class MasCentreServiceImpl implements MasCentreService {

    private final MasCentreRepo centreRepo;

    private final MasCapacityRepo capacityRepo;

    private final MasDistrictRepo districtRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasCentreServiceImpl.class);


    @Override
    public ApiResponse<MasCentreResponse> create(MasCentreRequest request) {
        try{

            Optional<MasCapacity> capacityOpt = capacityRepo.findById(request.getCapacityId());
            if(capacityOpt.isPresent()){
                Optional<MasDistrict> districtOpt = districtRepo.findById(request.getDistrictId());
                if(districtOpt.isPresent()){
                    MasCentre entity= new MasCentre();
                    entity.setCode(request.getCode());
                    entity.setName(request.getName());
                    entity.setNameInKannada(request.getNameInKannada());
                    entity.setContactNumber(request.getContactNumber());
                    entity.setAddress(request.getAddress());
                    entity.setType(request.getType());
                    entity.setProcessingCapacity(request.getProcessingCapacity());
                    entity.setGoDownCapacity(request.getGoDownCapacity());
                    entity.setHraPercentage(request.getHraPercentage());
                    entity.setBankRecipentCode(request.getBankRecipentCode());
//                entity.setCreatedBy("");
//                entity.setUpdatedBy("");
                    entity.setStatus("y");
                    entity.setUom(capacityOpt.get());
                    entity.setDistrict(districtOpt.get());
                    return ResponseUtils.createSuccessResponse(mapToResponse(centreRepo.save(entity)), new TypeReference<>() {});
                }
                return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CAPACITY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCentreResponse> updateById(Long id, MasCentreRequest request) {

        try{

            Optional<MasCentre> centreOpt = centreRepo.findById(id);
            if(centreOpt.isPresent()){
                Optional<MasDistrict> districtOpt = districtRepo.findById(request.getDistrictId());
                if(districtOpt.isPresent()){
                    Optional<MasCapacity> capacityOpt = capacityRepo.findById(request.getCapacityId());
                    if(capacityOpt.isPresent()){
                        MasCentre entity = centreOpt.get();
                        entity.setCode(request.getCode());
                        entity.setName(request.getName());
                        entity.setNameInKannada(request.getNameInKannada());
                        entity.setContactNumber(request.getContactNumber());
                        entity.setAddress(request.getAddress());
                        entity.setType(request.getType());
                        entity.setProcessingCapacity(request.getProcessingCapacity());
                        entity.setGoDownCapacity(request.getGoDownCapacity());
                        entity.setHraPercentage(request.getHraPercentage());
                        entity.setBankRecipentCode(request.getBankRecipentCode());
                        entity.setUom(capacityOpt.get());
                        entity.setDistrict(districtOpt.get());
                        //    entity.setUpdatedBy("");
                        return ResponseUtils.createSuccessResponse(mapToResponse(centreRepo.save(entity)), new TypeReference<>() {});
                    }
                    return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CAPACITY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }
                return ResponseUtils.createNotFoundResponse(FailureResponseMsg.DISTRICT_NOT_FOUND,HttpStatus.NOT_FOUND.value());
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CENTRE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCentreResponse> changeStatusById(Long id, String status) {
        try{

            Optional<MasCentre> centreOpt = centreRepo.findById(id);
            if(centreOpt.isPresent()){
                MasCentre entity = centreOpt.get();
                entity.setStatus(status);
//            entity.setUpdatedBy("");
                return ResponseUtils.createSuccessResponse(mapToResponse(centreRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CENTRE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCentreResponse> getById(Long id) {
        try{

            Optional<MasCentre> centreOpt = centreRepo.findById(id);
            if(centreOpt.isPresent()){
                return ResponseUtils.createSuccessResponse(mapToResponse(centreOpt.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CENTRE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasCentreResponse>> getAll(Integer flag) {
        try{

            List<MasCentre> statues;
            if(flag==1){
                statues=centreRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=centreRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasCentreResponse mapToResponse(MasCentre entity){
        MasCentreResponse response= new MasCentreResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        if (entity.getDistrict() != null) {
            response.setDistrictId(entity.getDistrict().getId());
        }
        if (entity.getUom() != null) {
            response.setCapacityId(entity.getUom().getId());
        }
        return  response;
    }
}

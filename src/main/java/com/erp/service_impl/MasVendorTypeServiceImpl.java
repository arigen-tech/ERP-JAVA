package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasVendorType;
import com.erp.repo.MasExternalAgencyTypeRepo;
import com.erp.repo.MasVendorTypeRepo;
import com.erp.request.MasVendorTypeReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasVendorTypeRes;
import com.erp.service.MasVendorTypeService;
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
public class MasVendorTypeServiceImpl implements MasVendorTypeService {

    @Autowired
    private MasVendorTypeRepo vendorTypeRepo;

    @Autowired
    private MasExternalAgencyTypeRepo agencyTypeRepo;

    private final Logger LOGGER = LoggerFactory.getLogger(MasVendorTypeServiceImpl.class);


    @Override
    public ApiResponse<MasVendorTypeRes> create(MasVendorTypeReq request) {
        try {

            MasVendorType vendorEn = new MasVendorType();
            vendorEn.setVendorTypeCode(request.getVendorTypeCode());
            vendorEn.setVendorTypeName(request.getVendorTypeName());
            vendorEn.setStatus("y");
//            vendorEn.setCreatedBy("");
//            vendorEn.setModifiedBy("");
//            vendorEn.setAgencyType();
            return ResponseUtils.createSuccessResponse(mapToResponse(vendorTypeRepo.save(vendorEn)), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {
                    },
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
    @Override
    public ApiResponse<MasVendorTypeRes> updateById(Long id, MasVendorTypeReq request) {
        try {

            Optional<MasVendorType> vendorOpt = vendorTypeRepo.findById(id);

            if(vendorOpt.isPresent()) {

                MasVendorType vendorEn = new MasVendorType();
                vendorEn.setVendorTypeCode(request.getVendorTypeCode());
                vendorEn.setVendorTypeName(request.getVendorTypeName());
//                vendorEn.setModifiedBy("");
                return ResponseUtils.createSuccessResponse(mapToResponse(vendorTypeRepo.save(vendorEn)), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VENDOR_TYPE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @Override
    public ApiResponse<MasVendorTypeRes> changeStatusById(Long id, String status) {
        try {
            Optional<MasVendorType> vendorOpt = vendorTypeRepo.findById(id);

            if(vendorOpt.isPresent()) {
                MasVendorType vendorEn = new MasVendorType();
                vendorEn.setStatus(status);
    //            vendorEn.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(vendorTypeRepo.save(vendorEn)), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VENDOR_TYPE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasVendorTypeRes> getById(Long id) {
        try {

            Optional<MasVendorType> vendorOpt = vendorTypeRepo.findById(id);
            if(vendorOpt.isPresent()) {
                return  ResponseUtils.createSuccessResponse(mapToResponse(vendorOpt.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.VENDOR_TYPE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasVendorTypeRes>> getAll(Integer flag) {
        try {

            List<MasVendorType> statues;
            if(flag==1){
                statues=vendorTypeRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=vendorTypeRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasVendorTypeRes mapToResponse(MasVendorType entity){
        MasVendorTypeRes response= new MasVendorTypeRes();

        response.setId(entity.getId());
        response.setVendorTypeCode(entity.getVendorTypeCode());
        response.setVendorTypeName(entity.getVendorTypeName());
        response.setStatus(entity.getStatus());

        return  response;
    }
}

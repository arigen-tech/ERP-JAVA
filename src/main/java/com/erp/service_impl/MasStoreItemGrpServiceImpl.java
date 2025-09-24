package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasStoreItemGroup;
import com.erp.repo.MasStoreItemGrpRepo;
import com.erp.request.MasStoreItemGrpReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasStoreItemGrpRes;
import com.erp.service.MasStoreItemGroupService;
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
public class MasStoreItemGrpServiceImpl implements MasStoreItemGroupService {

    @Autowired
    private MasStoreItemGrpRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasStoreItemGrpServiceImpl.class);

    @Override
    public ApiResponse<MasStoreItemGrpRes> create(MasStoreItemGrpReq request) {
        try {

            MasStoreItemGroup entity = new MasStoreItemGroup();
            entity.setItemGrpCode(request.getItemGrpCode());
            entity.setItemGrpName(request.getItemGrpName());
            entity.setStatus("y");
//            entity.setCreatedBy("");
//            entity.setModifiedBy("");
            return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasStoreItemGrpRes> updateById(Long id, MasStoreItemGrpReq request) {
        try {

            Optional<MasStoreItemGroup> byId = repo.findById(id);
            if(byId.isPresent()){

                MasStoreItemGroup entity = byId.get();
                entity.setItemGrpCode(request.getItemGrpCode());
                entity.setItemGrpName(request.getItemGrpName());
//                entity.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.STORE_ITEM_GRP_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasStoreItemGrpRes> changeStatusById(Long id, String status) {

        try {

            Optional<MasStoreItemGroup> byId = repo.findById(id);
            if(byId.isPresent()){
                MasStoreItemGroup entity = byId.get();
                entity.setStatus(status);
//                entity.setModifiedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.STORE_ITEM_GRP_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasStoreItemGrpRes> getById(Long id) {
        try {

            Optional<MasStoreItemGroup> byId = repo.findById(id);
            if(byId.isPresent()) {
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.STORE_ITEM_GRP_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasStoreItemGrpRes>> getAll(Integer flag) {
        try {

            List<MasStoreItemGroup> statues;
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

    private MasStoreItemGrpRes mapToResponse(MasStoreItemGroup entity){
        MasStoreItemGrpRes response = new MasStoreItemGrpRes();
        response.setItemGrpId(entity.getItemGrpId());
        response.setItemGrpCode(entity.getItemGrpCode());
        response.setItemGrpName(entity.getItemGrpName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

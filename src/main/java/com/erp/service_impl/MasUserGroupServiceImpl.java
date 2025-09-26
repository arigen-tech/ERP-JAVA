package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasStoreItemClass;
import com.erp.entity.MasUserGroup;
import com.erp.repo.MasUserGroupRepo;
import com.erp.request.MasUserGroupRequest;
import com.erp.response.ApiResponse;
import com.erp.service.MasUserGroupService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MasUserGroupServiceImpl implements MasUserGroupService {
    @Autowired
    private MasUserGroupRepo masUserGroupRepo;
    @Override
    public ApiResponse<MasUserGroup> create(MasUserGroupRequest request) {

        try{
            MasUserGroup masUserGroup=new MasUserGroup();
            masUserGroup.setGroupName(request.getGroupName());
            masUserGroup.setCode(request.getCode());
            masUserGroup.setStatus("y");
            masUserGroup.setCreatedOn(LocalDateTime.now());
            //   masUserGroup.setCreatedBy();
            masUserGroup.setUpdatedOn(LocalDateTime.now());
            //  masUserGroup.setUpdatedBy();
            return ResponseUtils.createSuccessResponse(masUserGroupRepo.save(masUserGroup), new TypeReference<>() {});
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasUserGroup> updateById(Long id, MasUserGroupRequest request) {
        Optional<MasUserGroup> masUserGroup=masUserGroupRepo.findById(id);
        try{
            if(masUserGroup.isPresent()){
                MasUserGroup masUserGroup1=masUserGroup.get();
                masUserGroup1.setGroupName(request.getGroupName());
                masUserGroup1.setCode(request.getCode());
                masUserGroup1.setStatus("y");
                masUserGroup1.setUpdatedOn(LocalDateTime.now());
                //  masUserGroup1.setUpdatedBy();
                return ResponseUtils.createSuccessResponse(masUserGroupRepo.save(masUserGroup1), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse("Mas User Group not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasUserGroup> changeStatusById(Long id, String status) {
        try{
            Optional<MasUserGroup> masUserGroup=masUserGroupRepo.findById(id);

            if(masUserGroup.isPresent()){
                MasUserGroup masUserGroup1=masUserGroup.get();
                if("y".equals(status)||"n".equals(status)){
                    masUserGroup1.setStatus(status);
                    // masStoreItemClass1.setUpdatedBy();
                    masUserGroup1.setUpdatedOn(LocalDateTime.now());

                }else{
                    return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                            "status not valid y or n",  HttpStatus.BAD_REQUEST.value());
                }
                return ResponseUtils.createSuccessResponse(masUserGroupRepo.save(masUserGroup1), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse("Mas User Group id not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasUserGroup> getById(Long id) {
        try {
            Optional<MasUserGroup> masUserGroup = masUserGroupRepo.findById(id);
            if (masUserGroup.isPresent()) {
                MasUserGroup masUserGroup1 =masUserGroup.get();
                return ResponseUtils.createSuccessResponse(masUserGroup1, new TypeReference<>() {
                });
            }
            return ResponseUtils.createNotFoundResponse("Mas User Group id not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasUserGroup>> getAll(Integer flag) {
        List<MasUserGroup> masUserGroups;
        try {
            if(flag==1){
                masUserGroups=masUserGroupRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                masUserGroups=masUserGroupRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},"flag not valid 0 or 1",HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse( masUserGroups, new TypeReference<>() {});

        } catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }
}

package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasStoreItemClass;
import com.erp.entity.MasTitle;
import com.erp.repo.MasStoreItemClassRepo;
import com.erp.request.MasStoreItemClassRequest;
import com.erp.response.ApiResponse;
import com.erp.service.MasStoreItemClassService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MasStoreItemClassImpl implements MasStoreItemClassService {
    @Autowired
    MasStoreItemClassRepo masStoreItemClassRepo;

    @Override
    public ApiResponse<MasStoreItemClass> create(MasStoreItemClassRequest request) {
        try{
        MasStoreItemClass masStoreItemClass=new MasStoreItemClass();
        masStoreItemClass.setItemClassCode(request.getItemClassCode());
        masStoreItemClass.setItemClassName(request.getItemClassName());
        masStoreItemClass.setItemClassNameKann(request.getItemClassNameKann());
        masStoreItemClass.setStatus("y");
        masStoreItemClass.setCreatedOn(LocalDateTime.now());
     //   masStoreItemClass.setCreatedBy();
        masStoreItemClass.setUpdatedOn(LocalDateTime.now());
      //  masStoreItemClass.setUpdatedBy();
        return ResponseUtils.createSuccessResponse(masStoreItemClassRepo.save(masStoreItemClass), new TypeReference<>() {});
    }catch (Exception e) {
        return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    }

    @Override
    public ApiResponse<MasStoreItemClass> updateById(Long id, MasStoreItemClassRequest request) {
        Optional<MasStoreItemClass> masStoreItemClass=masStoreItemClassRepo.findById(id);
        try{
        if(masStoreItemClass.isPresent()){
            MasStoreItemClass masStoreItemClass1=masStoreItemClass.get();
            masStoreItemClass1.setItemClassCode(request.getItemClassCode());
            masStoreItemClass1.setItemClassName(request.getItemClassName());
            masStoreItemClass1.setItemClassNameKann(request.getItemClassNameKann());
            masStoreItemClass1.setStatus("y");
            masStoreItemClass1.setUpdatedOn(LocalDateTime.now());
            //  masStoreItemClass.setUpdatedBy();
            return ResponseUtils.createSuccessResponse(masStoreItemClassRepo.save(masStoreItemClass1), new TypeReference<>() {});
        }
        return ResponseUtils.createNotFoundResponse("Mas Store Item Class not found", HttpStatus.NOT_FOUND.value());
    }catch (Exception e) {
        return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    }

    @Override
    public ApiResponse<MasStoreItemClass> changeStatusById(Long id, String status) {
        try{
        Optional<MasStoreItemClass> masStoreItemClass=masStoreItemClassRepo.findById(id);

        if(masStoreItemClass.isPresent()){
            MasStoreItemClass masStoreItemClass1=masStoreItemClass.get();
           if("y".equals(status)||"n".equals(status)){
               masStoreItemClass1.setStatus(status);
              // masStoreItemClass1.setUpdatedBy();
               masStoreItemClass1.setUpdatedOn(LocalDateTime.now());

           }else{
               return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                       "status not valid y or n",  HttpStatus.BAD_REQUEST.value());
           }
           return ResponseUtils.createSuccessResponse(masStoreItemClassRepo.save(masStoreItemClass1), new TypeReference<>() {});
        }
        return ResponseUtils.createNotFoundResponse("Mas Store Item Class id not found", HttpStatus.NOT_FOUND.value());
    }catch (Exception e) {
        return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
    }

    @Override
    public ApiResponse<MasStoreItemClass> getById(Long id) {
        try {
            Optional<MasStoreItemClass> masStoreItemClass = masStoreItemClassRepo.findById(id);
            if (masStoreItemClass.isPresent()) {
                MasStoreItemClass masStoreItemClass1 = masStoreItemClass.get();
                return ResponseUtils.createSuccessResponse(masStoreItemClass1, new TypeReference<>() {
                });
            }
            return ResponseUtils.createNotFoundResponse("Mas Store Item Class id not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                        FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
            }
    }

    @Override
    public ApiResponse<List<MasStoreItemClass>> getAll(Integer flag) {
       List<MasStoreItemClass> masStoreItemClasses;
        try {
            if(flag==1){
                masStoreItemClasses=masStoreItemClassRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                masStoreItemClasses=masStoreItemClassRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},"flag not valid 0 or 1",HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse( masStoreItemClasses, new TypeReference<>() {});

        } catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}

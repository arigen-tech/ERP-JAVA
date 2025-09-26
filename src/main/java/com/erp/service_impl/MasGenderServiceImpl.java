package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasGender;
import com.erp.entity.MasUserGroup;
import com.erp.repo.MasGenderRepo;
import com.erp.request.MasGenderRequest;
import com.erp.response.ApiResponse;
import com.erp.service.MasGenderService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MasGenderServiceImpl implements MasGenderService {
    @Autowired
    private MasGenderRepo masGenderRepo;


    @Override
    public ApiResponse<MasGender> create(MasGenderRequest request) {

            MasGender masGender=new MasGender();
            masGender.setGenderName(request.getGenderName());
            masGender.setGenderCode(request.getGenderCode());
            masGender.setStatus("y");
            masGender.setCreatedOn(LocalDateTime.now());
            //   masGender.setCreatedBy();
            masGender.setUpdatedOn(LocalDateTime.now());
            //  masGender.setUpdatedBy();
            return ResponseUtils.createSuccessResponse(masGenderRepo.save(masGender), new TypeReference<>() {});

    }

    @Override
    public ApiResponse<MasGender> updateById(Long id, MasGenderRequest request) {
        Optional<MasGender> masGender=masGenderRepo.findById(id);
        try{
            if(masGender.isPresent()){
                MasGender masGender1=masGender.get();
                masGender1.setGenderCode(request.getGenderCode());
                masGender1.setGenderName(request.getGenderName());
                masGender1.setStatus("y");
                masGender1.setUpdatedOn(LocalDateTime.now());
                //  masGender1.setUpdatedBy();
                return ResponseUtils.createSuccessResponse(masGenderRepo.save(masGender1), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse("Mas Gender not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasGender> changeStatusById(Long id, String status) {
        try{
            Optional<MasGender> masGender=masGenderRepo.findById(id);

            if(masGender.isPresent()){
                MasGender masGender1=masGender.get();
                if("y".equals(status)||"n".equals(status)){
                    masGender1.setStatus(status);
                    // masGender1.setUpdatedBy();
                    masGender1.setUpdatedOn(LocalDateTime.now());

                }else{
                    return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                            "status not valid y or n",  HttpStatus.BAD_REQUEST.value());
                }
                return ResponseUtils.createSuccessResponse(masGenderRepo.save(masGender1), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse("Mas Gender id not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasGender> getById(Long id) {
        try {
            Optional<MasGender> masGender = masGenderRepo.findById(id);
            if (masGender.isPresent()) {
                MasGender masGender1 =masGender.get();
                return ResponseUtils.createSuccessResponse(masGender1, new TypeReference<>() {
                });
            }
            return ResponseUtils.createNotFoundResponse("Mas Gender id not found", HttpStatus.NOT_FOUND.value());
        }catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
        public ApiResponse<List<MasGender>> getAll (Integer flag){
            List<MasGender> masGenders;
            try {
                if (flag == 1) {
                    masGenders = masGenderRepo.findByStatusIgnoreCase("y");
                } else if (flag == 0) {
                    masGenders = masGenderRepo.findByStatusInIgnoreCase(List.of("y", "n"));
                } else {
                    return ResponseUtils.createFailureResponse(null, new TypeReference<>() {
                    }, "flag not valid 0 or 1", HttpStatus.BAD_REQUEST.value());
                }
                return ResponseUtils.createSuccessResponse(masGenders, new TypeReference<>() {
                });

            } catch (Exception e) {
                return ResponseUtils.createFailureResponse(null, new TypeReference<>() {
                        },
                        FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

        }
    }

package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasItemCategory;
import com.erp.entity.MasItemSubcategory;
import com.erp.repo.MasItemCategoryRepo;
import com.erp.repo.MasItemSubcategoryRepo;
import com.erp.request.MasItemSubcategoryReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasItemSubcategoryRes;
import com.erp.service.MasItemSubcategoryService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MasItemSubcategoryServiceImpl implements MasItemSubcategoryService {

    @Autowired
    private MasItemCategoryRepo itemCategoryRepo;

    @Autowired
    private MasItemSubcategoryRepo itemSubcategoryRepo;


    @Override
    public ApiResponse<MasItemSubcategoryRes> create(MasItemSubcategoryReq request) {
        try {

            Optional<MasItemCategory> categoryOpt = itemCategoryRepo.findById(request.getItemCategoryId());
            if(categoryOpt.isPresent()){
                MasItemSubcategory entity= new MasItemSubcategory();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
                entity.setNameInKannada(request.getNameInKannada());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//                entity.setModifiedBy("");
                entity.setItemCategory(categoryOpt.get());

                return ResponseUtils.createSuccessResponse(mapToResponse(itemSubcategoryRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error("Unexpected Error :: ",e);
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }



    @Override
    public ApiResponse<MasItemSubcategoryRes> updateById(Long id, MasItemSubcategoryReq request) {
        try {
            Optional<MasItemSubcategory> itemSubcategoryOpt = itemSubcategoryRepo.findById(id);
            if (itemSubcategoryOpt.isPresent()) {
                Optional<MasItemCategory> categoryOpt = itemCategoryRepo.findById(request.getItemCategoryId());
                if (categoryOpt.isPresent()) {
                    MasItemSubcategory entity= itemSubcategoryOpt.get();
                    entity.setCode(request.getCode());
                    entity.setName(request.getName());
                    entity.setNameInKannada(request.getNameInKannada());
                    //                entity.setModifiedBy("");
                    entity.setItemCategory(categoryOpt.get());
                    return ResponseUtils.createSuccessResponse(mapToResponse(itemSubcategoryRepo.save(entity)), new TypeReference<>() {});
                } else {
                    return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_SUB_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error("Unexpected Error :: ",e);
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {
                    },
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasItemSubcategoryRes> changeStatusById(Long id, String status) {
        try {
            Optional<MasItemSubcategory> itemSubcategoryOpt = itemSubcategoryRepo.findById(id);
            if (itemSubcategoryOpt.isPresent()) {
                MasItemSubcategory entity= itemSubcategoryOpt.get();
                entity.setStatus(status);
//                entity.setModifiedBy("");
                return ResponseUtils.createSuccessResponse(mapToResponse(itemSubcategoryRepo.save(entity)), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_SUB_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error("Unexpected Error :: ",e);
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasItemSubcategoryRes> getById(Long id) {
        try {
            Optional<MasItemSubcategory> itemSubcategoryOpt = itemSubcategoryRepo.findById(id);
            if (itemSubcategoryOpt.isPresent()) {
                return ResponseUtils.createSuccessResponse(mapToResponse(itemSubcategoryOpt.get()), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_SUB_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error("Unexpected Error :: ",e);
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasItemSubcategoryRes>> getAll(Integer flag) {
        try {

            List<MasItemSubcategory> statues;
            if(flag==1){
                statues=itemSubcategoryRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=itemSubcategoryRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        } catch (Exception e) {
            log.error("Unexpected Error :: ",e);
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasItemSubcategoryRes mapToResponse(MasItemSubcategory entity) {
        MasItemSubcategoryRes res = new MasItemSubcategoryRes();

        res.setId(entity.getId());
        res.setCode(entity.getCode());
        res.setName(entity.getName());
        res.setStatus(entity.getStatus());
        res.setItemCategoryId(entity.getItemCategory().getId());

        return  res;
    }
}

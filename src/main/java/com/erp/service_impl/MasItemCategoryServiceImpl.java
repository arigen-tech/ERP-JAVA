package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasItemCategory;
import com.erp.entity.MasStoreItemGroup;
import com.erp.repo.MasItemCategoryRepo;
import com.erp.repo.MasStoreItemGrpRepo;
import com.erp.request.MasItemCategoryReq;
import com.erp.response.ApiResponse;
import com.erp.response.MasItemCategoryRes;
import com.erp.service.MasItemCategoryService;
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
public class MasItemCategoryServiceImpl implements MasItemCategoryService {

    @Autowired
    private MasItemCategoryRepo itemCategoryRepo;

    @Autowired
    private MasStoreItemGrpRepo itemGroupRepo;

    @Override
    public ApiResponse<MasItemCategoryRes> create(MasItemCategoryReq request) {
        try {

            Optional<MasStoreItemGroup> itemGroupOpt = itemGroupRepo.findById(request.getItemGroupId());
            if(itemGroupOpt.isPresent()){
                MasItemCategory entity= new MasItemCategory();
                entity.setCode(request.getCode());
                entity.setName(request.getName());
                entity.setNameInKannada(request.getNameInKannada());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//                entity.setModifiedBy("");
                entity.setItemGroup(itemGroupOpt.get());

                return ResponseUtils.createSuccessResponse(mapToResponse(itemCategoryRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STORE_ITEM_GRP_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasItemCategoryRes> updateById(Long id, MasItemCategoryReq request) {
        try {
            Optional<MasItemCategory> itemCategoryOpt = itemCategoryRepo.findById(id);
            if (itemCategoryOpt.isPresent()) {
                Optional<MasStoreItemGroup> itemGroupOpt = itemGroupRepo.findById(request.getItemGroupId());
                if (itemGroupOpt.isPresent()) {
                    MasItemCategory entity= itemCategoryOpt.get();
                    entity.setCode(request.getCode());
                    entity.setName(request.getName());
                    entity.setNameInKannada(request.getNameInKannada());
                    //                entity.setModifiedBy("");
                    entity.setItemGroup(itemGroupOpt.get());
                    return ResponseUtils.createSuccessResponse(mapToResponse(itemCategoryRepo.save(entity)), new TypeReference<>() {});
                } else {
                    return ResponseUtils.createNotFoundResponse(FailureResponseMsg.STORE_ITEM_GRP_NOT_FOUND, HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {
                    },
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasItemCategoryRes> changeStatusById(Long id, String status) {
        try {
            Optional<MasItemCategory> itemCategoryOpt = itemCategoryRepo.findById(id);
            if (itemCategoryOpt.isPresent()) {
                MasItemCategory entity= itemCategoryOpt.get();
                entity.setStatus(status);
//                entity.setModifiedBy("");
                return ResponseUtils.createSuccessResponse(mapToResponse(itemCategoryRepo.save(entity)), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

    }

    @Override
    public ApiResponse<MasItemCategoryRes> getById(Long id) {
        try {
            Optional<MasItemCategory> itemCategoryOpt = itemCategoryRepo.findById(id);
            if (itemCategoryOpt.isPresent()) {
                return ResponseUtils.createSuccessResponse(mapToResponse(itemCategoryOpt.get()), new TypeReference<>() {});
            }

            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.ITEM_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasItemCategoryRes>> getAll(Integer flag) {
        try {

            List<MasItemCategory> statues;
            if(flag==1){
                statues=itemCategoryRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=itemCategoryRepo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},FailureResponseMsg.FLAG_NOT_FOUND,HttpStatus.BAD_REQUEST.value());
            }
            return ResponseUtils.createSuccessResponse(statues.stream().map(this::mapToResponse).collect(Collectors.toList()), new TypeReference<>() {});

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasItemCategoryRes mapToResponse(MasItemCategory entity){
        MasItemCategoryRes res = new MasItemCategoryRes();

        res.setId(entity.getId());
        res.setCode(entity.getCode());
        res.setName(entity.getName());
        res.setStatus(entity.getStatus());
        res.setItemGroupId(entity.getItemGroup().getItemGrpId());

        return  res;
    }
}

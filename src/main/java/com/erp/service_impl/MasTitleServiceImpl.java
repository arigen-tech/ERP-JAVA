package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasTitle;
import com.erp.repo.MasTitleRepo;
import com.erp.request.MasTitleRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasTitleResponse;
import com.erp.service.MasTitleService;
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
public class MasTitleServiceImpl implements MasTitleService {

    @Autowired
    private MasTitleRepo repo;

    private final Logger LOGGER = LoggerFactory.getLogger(MasTitleServiceImpl.class);

    @Override
    public ApiResponse<MasTitleResponse> create(MasTitleRequest req) {
        try {

            MasTitle entity= new MasTitle();
            entity.setCode(req.getCode());
            entity.setName(req.getName());
            entity.setStatus("y");
//            entity.setCreatedBy("");
//        entity.setUpdatedBy("");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasTitleResponse> updateById(Long id, MasTitleRequest req) {
        try {

            Optional<MasTitle> byId = repo.findById(id);
            if(byId.isPresent()){
                MasTitle entity = byId.get();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                //            entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TITLE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasTitleResponse> changeStatusById(Long id, String status) {
        try {

            Optional<MasTitle> byId = repo.findById(id);
            if(byId.isPresent()){
                MasTitle entity = byId.get();
                entity.setStatus(status);
//            entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TITLE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasTitleResponse> getById(Long id) {
        try {

            Optional<MasTitle> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.TITLE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasTitleResponse>> getAll(Integer flag) {
        try {

            List<MasTitle> statues;
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

    private MasTitleResponse mapToResponse(MasTitle entity){
        MasTitleResponse response = new MasTitleResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

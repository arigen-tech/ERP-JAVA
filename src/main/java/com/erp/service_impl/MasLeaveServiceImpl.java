package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasLeave;
import com.erp.repo.MasLeaveRepo;
import com.erp.request.MasLeaveRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasLeaveResponse;
import com.erp.service.MasLeaveService;
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
public class MasLeaveServiceImpl implements MasLeaveService {

    private  final Logger LOGGER= LoggerFactory.getLogger(MasLeaveServiceImpl.class);

    @Autowired
    private MasLeaveRepo leaveRepo;

    @Override
    public ApiResponse<List<MasLeaveResponse>> getAll(Integer flag) {
       try {
               List<MasLeave> statuses;
               if (flag==1){
                   statuses = leaveRepo.findByStatusIgnoreCase("y");
               }else if(flag==0){
                   statuses=leaveRepo.findByStatusInIgnoreCase(List.of("y","n"));
               }else{
                   return  ResponseUtils.createFailureResponse(null, new TypeReference<List<MasLeaveResponse>>() {}, FailureResponseMsg.FLAG_NOT_FOUND, HttpStatus.BAD_REQUEST.value());
               }
               return ResponseUtils.createSuccessResponse(statuses.stream().map(this::mapToResponse).collect(Collectors.toList()),new TypeReference<>(){});
           } catch (Exception e) {
                LOGGER.error(e.getMessage());
               return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                       FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
           }
       }

    @Override
    public ApiResponse<MasLeaveResponse> changeStatusById(Long id, String status) {
        try {
            Optional<MasLeave> byId = leaveRepo.findById(id);
            if(byId.isPresent()){
                MasLeave entity = byId.get();
                entity.setStatus(status);
                MasLeave save = leaveRepo.save(entity);
                return ResponseUtils.createSuccessResponse(mapToResponse(save), new TypeReference<>(){});
            }
            return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.LEAVE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasLeaveResponse> findById(Long id) {
        try {
            Optional<MasLeave> byId = leaveRepo.findById(id);
            if(byId.isPresent()){
                MasLeave entity = byId.get();
                return  ResponseUtils.createSuccessResponse(mapToResponse(entity), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.LEAVE_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasLeaveResponse> add(MasLeaveRequest request) {
        try {
            MasLeave leave=new MasLeave();
            leave.setDescription(request.getDescription());
            leave.setStatus("y");
//            leave.setUpdatedBy("5");
//            leave.setCentreId();
            return ResponseUtils.createSuccessResponse(mapToResponse(leaveRepo.save(leave)), new TypeReference<>() {});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasLeaveResponse> updateById(Long id, MasLeaveRequest request) {
        try {
            Optional<MasLeave> byId = leaveRepo.findById(id);
            if(byId.isPresent()){
                MasLeave entity = byId.get();
                entity.setDescription(request.getDescription());
//                entity.setUpdatedBy();
                return  ResponseUtils.createSuccessResponse(mapToResponse(leaveRepo.save(entity)),new TypeReference<>(){});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.LEAVE_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasLeaveResponse mapToResponse(MasLeave entity){
        MasLeaveResponse response=new MasLeaveResponse();
        response.setId(entity.getId());
        response.setDescription(entity.getDescription());
        response.setStatus(entity.getStatus());
        return response;
    }
}

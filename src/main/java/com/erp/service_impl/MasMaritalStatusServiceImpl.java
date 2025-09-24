package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasMaritalStatus;
import com.erp.repo.MasMaritalStatusRepo;
import com.erp.request.MasMaritalStatusRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasMaritalStatusResponse;
import com.erp.service.MasMaritalStatusService;
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
//@Transactional
public class MasMaritalStatusServiceImpl implements MasMaritalStatusService {

    @Autowired
    private MasMaritalStatusRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasMaritalStatusServiceImpl.class);

    @Override
    public ApiResponse<MasMaritalStatusResponse> addMaritalStatus(MasMaritalStatusRequest request) {

        try {
            MasMaritalStatus entity= new MasMaritalStatus();
            entity.setStatusCode(request.getStatusCode());
            entity.setName(request.getName());
            entity.setStatus("y");
//            entity.setLastCngBy();
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR , HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasMaritalStatusResponse> changeMaritalStatus(Long id, String status) {
       try {
           Optional<MasMaritalStatus> byId = repo.findById(id);
           if(byId.isPresent()){
               MasMaritalStatus entity = byId.get();
               entity.setStatus(status);
//               entity.setLastCngBy();
               return ResponseUtils.createSuccessResponse( mapToResponse(repo.save(entity)), new TypeReference<>(){});
           }
           return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.MARTIAL_NOT_FOUND,404);
       }catch (Exception e){
           LOGGER.error(e.getMessage());
           return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                   FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
       }

    }

    @Override
    public ApiResponse<MasMaritalStatusResponse> editMaritalStatus(Long id, MasMaritalStatusRequest request) {
        try {
            Optional<MasMaritalStatus> byId = repo.findById(id);
            if(byId.isPresent()){
                MasMaritalStatus entity = byId.get();
                entity.setStatusCode(request.getStatusCode());
                entity.setName(request.getName());
//                entity.setLastCngBy();
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)),new TypeReference<>(){});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.MARTIAL_NOT_FOUND,404);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasMaritalStatusResponse> getMaritalStatusById(Long id) {

       try {
           Optional<MasMaritalStatus> byId = repo.findById(id);
           if(byId.isPresent()){
               MasMaritalStatus entity = byId.get();
               return  ResponseUtils.createSuccessResponse(mapToResponse(entity), new TypeReference<>() {});
           }else{
               return ResponseUtils.createNotFoundResponse(FailureResponseMsg.MARTIAL_NOT_FOUND,404);
           }
       } catch (Exception e) {
           LOGGER.error(e.getMessage());
           return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                   FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
       }
    }

    @Override
    public ApiResponse<List<MasMaritalStatusResponse>> getAllMaritalStatuses(Integer flag) {
        try {
            List<MasMaritalStatus> statuses;
            if (flag==1){
                statuses = repo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statuses=repo.findByStatusInIgnoreCase(List.of("y","n"));
            }else{
                return  ResponseUtils.createFailureResponse(null, new TypeReference<>() {},  FailureResponseMsg.FLAG_NOT_FOUND, 400);
            }
            return ResponseUtils.createSuccessResponse(statuses.stream().map(this::mapToResponse).collect(Collectors.toList()),new TypeReference<>(){});

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    private MasMaritalStatusResponse mapToResponse(MasMaritalStatus entity){
        MasMaritalStatusResponse response = new MasMaritalStatusResponse();
        response.setId(entity.getId());
        response.setStatusCode(entity.getStatusCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        return response;
    }
}

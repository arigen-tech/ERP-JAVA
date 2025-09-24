package com.erp.service_impl;


import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasHoliday;
import com.erp.repo.MasHolidayRepo;
import com.erp.request.MasHolidayRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasHolidayResponse;
import com.erp.service.MasHolidayService;
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
public class MasHolidayServiceImpl implements MasHolidayService {

    @Autowired
    private MasHolidayRepo repo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasHolidayServiceImpl.class);

    @Override
    public ApiResponse<MasHolidayResponse> create(MasHolidayRequest request) {

        try{
            MasHoliday entity= new MasHoliday();
            entity.setName(request.getName());
            entity.setYear(request.getYear());
            entity.setHolidayDate(request.getHolidayDate());
            entity.setRh(request.getRh());
            entity.setStatus("y");
//        entity.setUpdatedBy("");
            return ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasHolidayResponse> updateById(Long id, MasHolidayRequest request) {
        try{
            Optional<MasHoliday> byId = repo.findById(id);
            if(byId.isPresent()){
                MasHoliday entity = byId.get();
                entity.setName(request.getName());
                entity.setYear(request.getYear());
                entity.setHolidayDate(request.getHolidayDate());
                entity.setRh(request.getRh());
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.HOLIDAY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasHolidayResponse> changeStatusById(Long id, String status) {
        try{
            Optional<MasHoliday> byId = repo.findById(id);
            if(byId.isPresent()){
                MasHoliday entity = byId.get();
                entity.setStatus(status);
                //        entity.setUpdatedBy("");
                return  ResponseUtils.createSuccessResponse(mapToResponse(repo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.HOLIDAY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasHolidayResponse> getById(Long id) {
        try{
            Optional<MasHoliday> byId = repo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.HOLIDAY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<List<MasHolidayResponse>> getAll(Integer flag) {
        try{
            List<MasHoliday> statues;
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

    private MasHolidayResponse mapToResponse(MasHoliday entity){
        MasHolidayResponse response= new MasHolidayResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setYear(entity.getYear());
        response.setRh(entity.getRh());
        response.setHolidayDate(entity.getHolidayDate());
        response.setStatus(entity.getStatus());
        return  response;
    }
}

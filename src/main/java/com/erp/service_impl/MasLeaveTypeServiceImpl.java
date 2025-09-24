package com.erp.service_impl;


import com.erp.entity.MasLeave;
import com.erp.entity.MasLeaveType;
import com.erp.repo.MasLeaveRepo;
import com.erp.repo.MasLeaveTypeRepo;
import com.erp.request.MasLeaveTypeRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasLeaveTypeResponse;
import com.erp.service.MasLeaveTypeService;
import com.erp.util.ResponseUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MasLeaveTypeServiceImpl implements MasLeaveTypeService {

    private  final MasLeaveRepo leaveRepo;

    private  final MasLeaveTypeRepo leaveTypeRepo;


    @Override
    public ApiResponse<MasLeaveTypeResponse> create(MasLeaveTypeRequest req) {
        try {
            Optional<MasLeave> leaveOpt = leaveRepo.findById(req.getLeaveId());
            if(leaveOpt.isPresent()){
                MasLeaveType entity= new MasLeaveType();
                entity.setEncashable(req.getEncashable());
                entity.setAllowedDays(req.getAllowedDays());
                entity.setValidFromDate(req.getValidFromDate());
                entity.setValidToDate(req.getValidToDate());
                entity.setRemarks(req.getRemarks());
                entity.setCarryForward(req.getCarryForward());
                entity.setEnchFormula(req.getEnchFormula());
                entity.setBufferRequired(req.getBufferRequired());
                entity.setEncashablePercent(req.getEncashablePercent());
                entity.setMonthOrYear(req.getMonthOrYear());
                entity.setHalfDayAllowed(req.getHalfDayAllowed());
                entity.setStatus("y");
                entity.setLeave(leaveOpt.get());
                return  ResponseUtils.createSuccessResponse(mapToResponse(leaveTypeRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse("Invalid Leave ID, Leave Status not Found",404);

        } catch (Exception e) {
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    "An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasLeaveTypeResponse> updateById(Long id, MasLeaveTypeRequest req) {
        return null;
    }

    @Override
    public ApiResponse<MasLeaveTypeResponse> changeStatusById(Long id, String status) {
        return null;
    }

    @Override
    public ApiResponse<MasLeaveTypeResponse> getById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<List<MasLeaveTypeResponse>> getAll(Integer flag) {
        return null;
    }

    private MasLeaveTypeResponse mapToResponse(MasLeaveType entity){
        MasLeaveTypeResponse response= new MasLeaveTypeResponse();
        BeanUtils.copyProperties(entity,response);
//        response.setLeaveType(entity.getLeave().getDescription());
        return  response;
    }
}

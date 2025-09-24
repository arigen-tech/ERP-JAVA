package com.erp.service_impl;

import com.erp.constant.FailureResponseMsg;
import com.erp.entity.MasCountry;
import com.erp.entity.MasCurrency;
import com.erp.repo.MasCountryRepo;
import com.erp.repo.MasCurrencyRepo;
import com.erp.request.MasCountryRequest;
import com.erp.response.ApiResponse;
import com.erp.response.MasCountryResponse;
import com.erp.service.MasCountryService;
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
public class MasCountryServiceImpl implements MasCountryService {

    @Autowired
    private MasCountryRepo countryRepo;

    @Autowired
    private MasCurrencyRepo currencyRepo;

    private final Logger LOGGER= LoggerFactory.getLogger(MasCountryServiceImpl.class);


    @Override
    public ApiResponse<MasCountryResponse> create(MasCountryRequest req) {
        try {
            Optional<MasCurrency> byId = currencyRepo.findById(req.getCurrencyId());
            if(byId.isPresent()){
                MasCountry entity=new MasCountry();
                entity.setCode(req.getCode());
                entity.setName(req.getName());
                entity.setStatus("y");
//                entity.setCreatedBy("");
//            entity.setUpdatedBy("");
                entity.setCurrency(byId.get());
                return ResponseUtils.createSuccessResponse(mapToResponse(countryRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.CURRENCY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCountryResponse> updateById(Long id, MasCountryRequest req) {
        try {
            Optional<MasCountry> country = countryRepo.findById(id);
            if(country.isPresent()){
                Optional<MasCurrency> currency = currencyRepo.findById(req.getCurrencyId());
                if(currency.isPresent()){
                    MasCountry entity = country.get();
                    entity.setCode(req.getCode());
                    entity.setName(req.getName());
//                entity.setUpdatedBy("");
                    entity.setCurrency(currency.get());
                    return  ResponseUtils.createSuccessResponse(mapToResponse(countryRepo.save(entity)), new TypeReference<>() {});
                }else{
                    return  ResponseUtils.createNotFoundResponse(FailureResponseMsg.CURRENCY_NOT_FOUND,HttpStatus.NOT_FOUND.value());
                }

            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COUNTRY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCountryResponse> changeStatusById(Long id, String status) {

        try {
            Optional<MasCountry> byId = countryRepo.findById(id);
            if(byId.isPresent()){
                MasCountry entity = byId.get();
                entity.setStatus(status);
                return  ResponseUtils.createSuccessResponse(mapToResponse(countryRepo.save(entity)), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COUNTRY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    @Override
    public ApiResponse<MasCountryResponse> getById(Long id) {
        try {
            Optional<MasCountry> byId = countryRepo.findById(id);
            if(byId.isPresent()){
                return  ResponseUtils.createSuccessResponse(mapToResponse(byId.get()), new TypeReference<>() {});
            }
            return ResponseUtils.createNotFoundResponse(FailureResponseMsg.COUNTRY_NOT_FOUND,HttpStatus.NOT_FOUND.value());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseUtils.createFailureResponse(null, new TypeReference<>() {},
                    FailureResponseMsg.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }


    @Override
    public ApiResponse<List<MasCountryResponse>> getAll(Integer flag) {
        try {
            List<MasCountry> statues;
            if(flag==1){
                statues=countryRepo.findByStatusIgnoreCase("y");
            }else if(flag==0){
                statues=countryRepo.findByStatusInIgnoreCase(List.of("y","n"));
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

    private MasCountryResponse mapToResponse(MasCountry entity){
        MasCountryResponse response= new MasCountryResponse();
        response.setId(entity.getId());
        response.setCode(entity.getCode());
        response.setName(entity.getName());
        response.setStatus(entity.getStatus());
        response.setCurrencyId(entity.getCurrency().getId());
        return  response;
    }
}

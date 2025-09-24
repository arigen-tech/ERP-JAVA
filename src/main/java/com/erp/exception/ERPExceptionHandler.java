package com.erp.exception;


import com.erp.response.ApiError;
import com.erp.response.ApiResponse;
import com.erp.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ERPExceptionHandler {

    @ExceptionHandler(ERPException.class)
    public ResponseEntity<?> handleEPRException(ERPException exception){
        ApiResponse<ApiError> errApiResponse=new ApiResponse<>();
        errApiResponse.setMessage(exception.getMessage());
        errApiResponse.setStatus(exception.getStatus());
        ApiError error=new ApiError();
        error.setMessage(exception.getMessage());
        errApiResponse.setResponse(error);
        return new ResponseEntity<>(errApiResponse, HttpStatusCode.valueOf(exception.getStatus()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ApiError>> handleValidationException(MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());

        // Collect all field errors into list
        List<String> fields = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .toList();

        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        ApiError error = new ApiError();
        error.setField(fields.toArray(new String[0]));
        error.setMessage(String.join(", ", messages));

        ApiResponse<ApiError> errApiResponse =
                ResponseUtils.createFailureResponse(error, null, "Validation failed", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errApiResponse, HttpStatus.BAD_REQUEST);
    }



}

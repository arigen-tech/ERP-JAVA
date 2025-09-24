package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasEmployeeStatusRequest {


    @NotBlank(message = "Employee status code is required")
    @Size(max = 30, message = "Employee status code must not exceed 30 characters")
    private String code;

    @NotBlank(message = "Employee status code is required")
    @Size(max = 50, message = "Employee status code must not exceed 50 characters")
    private String name;
}

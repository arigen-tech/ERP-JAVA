package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasExternalAgencyTypeReq {

    @NotBlank(message = "External agency type code is required")
    @Size(max = 25, message = "External agency type code must not exceed 25 characters")
    private String code;

    @NotBlank(message = "External agency type name is required")
    @Size(max = 50, message = "External agency type name must not exceed 50 characters")
    private String name;
}

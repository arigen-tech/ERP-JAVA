package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasDistrictRequest {

    @NotBlank(message = "District code is required")
    @Size(max = 50, message = "District code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "District name is required")
    @Size(max = 100, message = "District name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "District name in Kannada is required")
    @Size(max = 100, message = "District name in Kannada must not exceed 100 characters")
    private String nameInKannada;

    @NotNull(message = "State ID is required")
    private Long stateId;
}

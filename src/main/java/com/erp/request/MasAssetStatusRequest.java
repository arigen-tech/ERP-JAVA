package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasAssetStatusRequest {


    @NotBlank(message = "Asset status code is required")
    @Size(max = 10, message = "Asset status code must not exceed 10 characters")
    private String code;

    @NotBlank(message = "Asset status name is required")
    @Size(max = 100, message = "Asset status name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Asset status description is required")
    @Size(max = 200, message = "Asset status description must not exceed 200 characters")
    private String description;
}

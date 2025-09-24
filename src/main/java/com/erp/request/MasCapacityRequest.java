package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasCapacityRequest {

    @NotBlank(message = "Unit code is required")
    @Size(max = 20, message = "Unit code must not exceed 20 characters")
    private String code;

    @NotBlank(message = "Unit name is required")
    @Size(max = 50, message = "Unit name must not exceed 50 characters")
    private  String name;

    @NotBlank(message = "Unit type is required")
    @Size(max = 20, message = "type code must not exceed 20 characters")
    private String type;
}

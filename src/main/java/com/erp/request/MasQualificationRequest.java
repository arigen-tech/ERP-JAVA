package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasQualificationRequest {

    @NotBlank(message = "Qualification status code is required")
    @Size(max = 50, message = "Qualification status code must not exceed 50 characters")
    private String code;

    @NotBlank(message = "Qualification status name is required")
    @Size(max = 100, message = "Qualification status name must not exceed 100 characters")
    private String name;
}

package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasReligionRequest {

    @NotBlank(message = "Religion status code is required")
    @Size(max = 10, message = "Religion status code must not exceed 10 characters")
    private String code;

    @NotBlank(message = "Religion status name is required")
    @Size(max = 255, message = "Religion status name must not exceed 255 characters")
    private String name;

}

package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasSubcasteRequest {

    @NotBlank(message = "Sub caste code is required")
    @Size(max = 15, message = "Sub caste code must not exceed 15 characters")
    private String code;

    @NotBlank(message = "Sub caste name is required")
    @Size(max = 100, message = "Sub caste name must not exceed 100 characters")
    private String name;

    @NotNull(message = "Caste ID must be required")
    private Long casteId;
}

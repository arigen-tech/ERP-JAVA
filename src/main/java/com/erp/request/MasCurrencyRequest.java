package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasCurrencyRequest {

    @NotBlank(message = "Currency code is required")
    @Size(max = 50, message = "Currency code cannot exceed 50 characters")
    private String code;

    @NotBlank(message = "Currency name is required")
    @Size(max = 50, message = "Currency name cannot exceed 50 characters")
    private String name;
}

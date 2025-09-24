package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasVillageRequest {

    @NotBlank(message = "Village code is required")
    @Size(max = 5, message = "Village code cannot exceed 5 characters")
    private String code;

    @NotBlank(message = "Village name is required")
    @Size(max = 105, message = "Village name cannot exceed 105 characters")
    private String name;

    @NotBlank(message = "Village name in Kannada is required")
    @Size(max = 105, message = "Village name in Kannada cannot exceed 105 characters")
    private String nameInKannada;

    @NotNull(message = "Taluka ID is required")
    private Long talukaId;
}

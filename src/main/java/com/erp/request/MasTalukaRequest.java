package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasTalukaRequest {

    @NotBlank(message = "Taluka code is required")
    @Size(max = 50, message = "Taluka code cannot exceed 50 characters")
    private String code;

    @NotBlank(message = "Taluka name is required")
    @Size(max = 100, message = "Taluka name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Taluka name in Kannada is required")
    @Size(max = 100, message = "Taluka name in Kannada cannot exceed 100 characters")
    private String nameInKannada;

    @NotNull(message = "District ID is required")
    private Long districtId;
}

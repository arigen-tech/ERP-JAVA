package com.erp.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MasAssetCategoryRequest {

    @NotBlank(message = "Asset category code is required")
    @Size(max = 10, message = "Asset category code must not exceed 10 characters")
    private String code;

    @NotBlank(message = "Asset category Name is required")
    @Size(max = 100, message = "Asset category Name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Asset category type is required")
    @Size(max = 15, message = "Asset category type must not exceed 15 characters")
    private String type;

    @NotBlank(message = "Asset category description is required")
    @Size(max = 250, message = "Asset category description must not exceed 250 characters")
    private String description;

    @NotBlank(message = "Asset category remarks is required")
    @Size(max = 100, message = "Asset category remarks must not exceed 100 characters")
    private String remarks;

    @NotNull(message = "Asset depreciation  is required")
    @PositiveOrZero( message = "depreciation must be greater than 0")
    private BigDecimal depreciation;

}

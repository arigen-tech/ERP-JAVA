package com.erp.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MasCentreRequest {

    @NotBlank(message = "Centre code is required")
    @Size(max = 100, message = "Centre code cannot exceed 100 characters")
    private String code;

    @NotBlank(message = "Centre name is required")
    @Size(max = 200, message = "Centre name cannot exceed 200 characters")
    private String name;

    @NotBlank(message = "Centre name in Kannada is required")
    @Size(max = 200, message = "Centre name in Kannada cannot exceed 200 characters")
    private String nameInKannada;

    @NotBlank(message = "Contact number is required")
    @Size(max = 15, message = "Contact number cannot exceed 15 characters")
    private String contactNumber;

    @NotBlank(message = "Address is required")
    @Size(max = 300, message = "Address cannot exceed 300 characters")
    private String address;

    @NotBlank(message = "Centre type is required")
    @Size(max = 20, message = "Centre type cannot exceed 20 characters")
    private String type;

    @NotNull(message = "Processing capacity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Processing capacity must be greater than 0")
    private BigDecimal processingCapacity;

    @NotNull(message = "Unit capacity Id is required")
    private Long capacityId;

    @NotNull(message = "Go-down capacity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Go-down capacity must be greater than 0")
    private BigDecimal goDownCapacity;

    @NotNull(message = "District Id is required")
    private Long districtId;

    @NotNull(message = "HRA percentage is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "HRA percentage must be 0 or more")
    @DecimalMax(value = "100.0", inclusive = true, message = "HRA percentage must not exceed 100")
    private BigDecimal hraPercentage;

    @NotBlank(message = "Bank recipient code is required")
    @Size(max = 30, message = "Bank recipient code cannot exceed 30 characters")
    private String bankRecipentCode;
}

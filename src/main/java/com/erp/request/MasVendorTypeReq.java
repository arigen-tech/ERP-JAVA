package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasVendorTypeReq {

    @NotBlank(message = "Vendor type code is required")
    @Size(max = 20, message = "Vendor type code must not exceed 20 characters")
    private String vendorTypeCode;

    @NotBlank(message = "Vendor type name is required")
    @Size(max = 100, message = "Vendor type name must not exceed 100 characters")
    private String vendorTypeName;
}

package com.erp.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasVendorTypeRes {

    private Long id;
    private String vendorTypeCode;
    private String vendorTypeName;
    private String status;
}

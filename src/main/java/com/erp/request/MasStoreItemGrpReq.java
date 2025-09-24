package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasStoreItemGrpReq {

    @NotBlank(message = "Item Group code is required")
    @Size(max = 20, message = "Item Group code must not exceed 20 characters")
    private String itemGrpCode;

    @NotBlank(message = "Item Group name is required")
    @Size(max = 50, message = "Item Group name must not exceed 50 characters")
    private String itemGrpName;


}

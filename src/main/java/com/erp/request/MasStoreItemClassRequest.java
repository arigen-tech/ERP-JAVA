package com.erp.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MasStoreItemClassRequest {
    private String itemClassCode;

    @Size(max = 50, message = "State name in Kannada cannot exceed 100 characters")
    private String itemClassName;


    private String itemClassNameKann;
}

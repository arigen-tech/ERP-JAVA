package com.erp.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MasGenderRequest {
    private String genderName;

    @Size(max=1, message="gender only one character")
    private String genderCode;

}

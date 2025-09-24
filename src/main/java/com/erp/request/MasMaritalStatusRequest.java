package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasMaritalStatusRequest {

        @NotBlank(message = "Marital status code is required")
        @Size(max = 30, message = "Marital status code must not exceed 30 characters")
        private String statusCode;

        @NotBlank(message = "Marital status name is required")
        @Size(max = 30, message = "Marital status name must not exceed 30 characters")
        private String name;


}

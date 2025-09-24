package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasRelationRequest {

    @NotBlank(message = "Relation code is required")
    @Size(max = 20, message = "Relation code must not exceed 20 characters")
    private String statusCode;

    @NotBlank(message = "Relation name is required")
    @Size(max = 50, message = "Relation name must not exceed 50 characters")
    private String statusName;
}

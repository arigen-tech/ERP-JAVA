package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasLeaveRequest {

    @NotBlank(message = "Leave description is required")
    @Size(max = 250, message = "Description cannot exceed 250 characters")
    private String description;

}

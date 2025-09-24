package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasInstituteRequest {

    @NotBlank(message = "Institute code is required")
    @Size(max = 50, message = "Institute code cannot exceed 50 characters")
    private String code;

    @NotBlank(message = "Institute name is required")
    @Size(max = 100, message = "Institute name cannot exceed 100 characters")
    private String name;
}

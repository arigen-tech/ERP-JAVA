package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasGradeRequest {

    @NotBlank(message = "Grade code is required")
    @Size(max = 30, message = "Grade code cannot exceed 30 characters")
    private String code;

    @NotBlank(message = "Grade name is required")
    @Size(max = 50, message = "Grade name cannot exceed 50 characters")
    private String name;

}

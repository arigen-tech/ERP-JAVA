package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasCasteRequest {


    @NotBlank(message = "Caste code is required")
    @Size(max = 15, message = "Caste code must not exceed 15 characters")
    private String code;

    @NotBlank(message = "Caste name is required")
    @Size(max = 100, message = "Caste Name must not exceed 100 characters")
    private String name;
}

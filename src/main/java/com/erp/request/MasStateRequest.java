package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasStateRequest {

    @NotBlank(message = "State code is required")
    @Size(max = 50, message = "State code cannot exceed 50 characters")
    private String code;

    @NotBlank(message = "State name is required")
    @Size(max = 100, message = "State name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "State name in Kannada is required")
    @Size(max = 100, message = "State name in Kannada cannot exceed 100 characters")
    private String nameInKannada;

    @NotNull(message = "Country ID is required")
    private Long countryId;
}

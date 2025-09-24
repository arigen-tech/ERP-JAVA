package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasCountryRequest {

    @NotBlank(message = "Country code is required")
    @Size(max = 10, message = "Country code cannot exceed 10 characters")
    private String code;

    @NotBlank(message = "Country name is required")
    @Size(max = 100, message = "Country name cannot exceed 100 characters")
    private String name;

    @NotNull(message = "Currency ID is required")
    private Long currencyId;
}

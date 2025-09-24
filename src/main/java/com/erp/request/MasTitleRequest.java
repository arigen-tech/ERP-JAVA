package com.erp.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasTitleRequest {

    @NotBlank(message = "Title code is required")
    @Size(max = 10, message = "Title code must not exceed 10 characters")
    private String code;

    @NotBlank(message = "Title name is required")
    @Size(max = 50, message = "Title name must not exceed 50 characters")
    private String name;

}

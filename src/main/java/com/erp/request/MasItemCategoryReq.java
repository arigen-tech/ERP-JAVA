package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasItemCategoryReq {

    @NotBlank(message = "Item Category code is required")
    @Size(max = 20, message = "Item Category code cannot exceed 20 characters")
    private String code;

    @NotBlank(message = "Item Category name is required")
    @Size(max = 150, message = "Item Category name cannot exceed 150 characters")
    private String name;

    @NotBlank(message = "Item Category name in Kannada is required")
    @Size(max = 150, message = "Item Category name in Kannada cannot exceed 150 characters")
    private String nameInKannada;

    @NotNull(message = "Item Group ID is required")
    private Long itemGroupId;
}

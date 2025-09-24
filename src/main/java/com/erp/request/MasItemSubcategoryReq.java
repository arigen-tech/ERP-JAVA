package com.erp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasItemSubcategoryReq {

    @NotBlank(message = "Item Subcategory code is required")
    @Size(max = 20, message = "Item Subcategory code cannot exceed 20 characters")
    private String code;

    @NotBlank(message = "Item Subcategory name is required")
    @Size(max = 150, message = "Item Subcategory name cannot exceed 150 characters")
    private String name;

    @NotBlank(message = "Item Subcategory name in Kannada is required")
    @Size(max = 150, message = "Item Subcategory name in Kannada cannot exceed 150 characters")
    private String nameInKannada;

    @NotNull(message = "Item Category ID is required")
    private Long itemCategoryId;
}
